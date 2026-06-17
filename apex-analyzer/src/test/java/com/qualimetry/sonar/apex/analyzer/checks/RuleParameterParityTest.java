/*
 * Copyright 2026 SHAZAM Analytics Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.junit.jupiter.api.Test;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Guards that documented configuration and native rule parameters never drift apart.
 * <p>
 * For every check, the {@code @RuleProperty} fields declared in code must exactly match the
 * parameters listed in the rule's HTML {@code <h2>Configuration</h2>} table (same key, same
 * default). A documented knob with no backing parameter, or a parameter the docs omit, fails.
 */
class RuleParameterParityTest {

    private static final String RESOURCE_BASE = "/com/qualimetry/sonar/apex/analyzer/checks/";

    private static final Pattern CONFIG_SECTION = Pattern.compile(
            "<h2>\\s*Configuration\\s*</h2>(.*?)(?=<h2>|\\z)", Pattern.DOTALL);
    private static final Pattern TABLE_ROW = Pattern.compile(
            "<tr>\\s*<td>([^<]*)</td>\\s*<td>[^<]*</td>\\s*<td>([^<]*)</td>\\s*</tr>", Pattern.DOTALL);

    @Test
    void everyRulePropertyMatchesItsDocumentedConfiguration() {
        Map<String, String> mismatches = new TreeMap<>();
        for (Class<? extends BaseCheck> check : CheckList.getAllChecks()) {
            String ruleKey = check.getAnnotation(Rule.class).key();
            Map<String, String> codeParams = ruleParameters(check);
            Map<String, String> docParams = documentedParameters(ruleKey);
            if (!codeParams.equals(docParams)) {
                mismatches.put(ruleKey, "code=" + codeParams + " doc=" + docParams);
            }
        }
        assertThat(mismatches)
                .as("Each rule's HTML Configuration table must match its @RuleProperty fields (key + default)")
                .isEmpty();
    }

    @Test
    void documentedConfigurableRulesExposeNativeParameters() {
        // Sanity floor: the repaired rules must actually carry parameters, so the parity test
        // above is meaningful and cannot pass by both sides being empty.
        long withParams = CheckList.getAllChecks().stream()
                .filter(c -> !ruleParameters(c).isEmpty())
                .count();
        assertThat(withParams).isGreaterThanOrEqualTo(19);
    }

    private static Map<String, String> ruleParameters(Class<?> check) {
        Map<String, String> params = new TreeMap<>();
        for (Field field : check.getDeclaredFields()) {
            RuleProperty property = field.getAnnotation(RuleProperty.class);
            if (property != null) {
                String key = property.key().isEmpty() ? field.getName() : property.key();
                params.put(key, property.defaultValue());
            }
        }
        return params;
    }

    private Map<String, String> documentedParameters(String ruleKey) {
        Map<String, String> params = new TreeMap<>();
        String html = readHtml(ruleKey);
        if (html == null) {
            return params;
        }
        Matcher section = CONFIG_SECTION.matcher(html);
        if (!section.find()) {
            return params;
        }
        Matcher row = TABLE_ROW.matcher(section.group(1));
        while (row.find()) {
            params.put(row.group(1).trim(), row.group(2).trim());
        }
        return params;
    }

    private String readHtml(String ruleKey) {
        try (InputStream is = getClass().getResourceAsStream(RESOURCE_BASE + ruleKey + ".html")) {
            if (is == null) {
                return null;
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new AssertionError("Failed to read HTML for rule " + ruleKey, e);
        }
    }
}
