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
import org.sonar.check.RuleProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Variable and parameter names must conform to the configured pattern.
 */
@Rule(
        key = "qa-convention-variable-naming-pattern",
        name = "Variable/parameter names must conform to pattern",
        description = "Detects variable and parameter names that do not conform to the configured naming pattern.",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class VariableNamingPatternCheck extends BaseCheck {

    private static final String DEFAULT_FORMAT = "^[a-z][a-zA-Z0-9]*$";

    @RuleProperty(
            key = "format",
            description = "Regular expression that variable and parameter names must match",
            defaultValue = DEFAULT_FORMAT)
    private String format = DEFAULT_FORMAT;

    private Pattern compiledFormat;

    public void setFormat(String format) {
        this.format = format;
        this.compiledFormat = null;
    }

    private Pattern formatPattern() {
        if (compiledFormat == null) {
            try {
                compiledFormat = Pattern.compile(format);
            } catch (PatternSyntaxException e) {
                compiledFormat = Pattern.compile(DEFAULT_FORMAT);
            }
        }
        return compiledFormat;
    }

    private static final Pattern VAR_OR_PARAM = Pattern.compile("\\b(?:Integer|String|Boolean|Id|List<[^>]+>|Set<[^>]+>|Map<[^>]+>|\\w+)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*[=;,)]");

    @Override
    public void scan(InputFile file, String content) {
        Pattern pattern = formatPattern();
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = normalized.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Matcher m = VAR_OR_PARAM.matcher(lines[i]);
            while (m.find()) {
                String name = m.group(1);
                if (name.length() < 2) continue;
                if (name.equals(name.toUpperCase()) && name.contains("_")) continue;
                if (!pattern.matcher(name).matches() && !name.equals(name.toUpperCase())) {
                    addIssue(i + 1, "Variable/parameter '" + name + "' should follow camelCase.");
                }
            }
        }
    }
}
