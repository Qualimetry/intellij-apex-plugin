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
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Pattern;

/**
 * @AuraEnabled getter must be public.
 */
@Rule(
        key = "qa-error-inaccessible-aura-getter",
        name = "@AuraEnabled getter must be public",
        description = "Flags @AuraEnabled members that are not public or global, preventing the Lightning framework from accessing them.",
        tags = {"error-prone", "salesforce", "lightning"},
        priority = Priority.CRITICAL
)
public class InaccessibleAuraGetterCheck extends BaseCheck {

    private static final Pattern AURA_ENABLED = Pattern.compile("@AuraEnabled\\b");
    private static final Pattern PUBLIC_OR_GLOBAL = Pattern.compile("(?i)\\b(public|global)\\s+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!AURA_ENABLED.matcher(lines[i]).find()) continue;
            int declLine = findNextDeclaration(lines, i + 1);
            if (declLine < 0) continue;
            if (!PUBLIC_OR_GLOBAL.matcher(lines[declLine]).find()) {
                addIssue(i + 1, "@AuraEnabled member must be public or global.");
            }
        }
    }

    private static int findNextDeclaration(String[] lines, int from) {
        for (int j = from; j < lines.length; j++) {
            String trimmed = lines[j].trim();
            if (trimmed.isEmpty() || trimmed.startsWith("@")) continue;
            return j;
        }
        return -1;
    }
}
