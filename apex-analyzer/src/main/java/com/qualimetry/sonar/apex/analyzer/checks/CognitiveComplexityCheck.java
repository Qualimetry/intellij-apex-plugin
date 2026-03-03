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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Method cognitive complexity must not exceed threshold.
 */
@Rule(
        key = "qa-complexity-cognitive",
        name = "Method cognitive complexity must not exceed threshold",
        description = "High cognitive complexity penalizes deeply nested logic, measuring the mental effort to understand a method",
        tags = {"complexity", "brain-overload"},
        priority = Priority.CRITICAL
)
public class CognitiveComplexityCheck extends BaseCheck {

    private static final int DEFAULT_THRESHOLD = 15;

    private static final Pattern METHOD_DECL = Pattern.compile(
            "(?i)\\b\\w+\\s+" +
            "(?!if\\b|else\\b|for\\b|while\\b|switch\\b|catch\\b|try\\b|do\\b|new\\b|class\\b|return\\b)" +
            "\\w+\\s*\\([^)]*\\)\\s*\\{");
    private static final Pattern ELSE_IF = Pattern.compile("(?i)\\}?\\s*else\\s+if\\b");
    private static final Pattern ELSE_ONLY = Pattern.compile("(?i)\\}?\\s*else\\s*\\{");
    private static final Pattern IF_KW = Pattern.compile("(?i)\\bif\\b");
    private static final Pattern FOR_KW = Pattern.compile("(?i)\\bfor\\b");
    private static final Pattern WHILE_KW = Pattern.compile("(?i)\\bwhile\\b");
    private static final Pattern CATCH_KW = Pattern.compile("(?i)\\bcatch\\b");
    private static final Pattern SWITCH_KW = Pattern.compile("(?i)\\bswitch\\b");
    private static final Pattern AND_OP = Pattern.compile("&&");
    private static final Pattern OR_OP = Pattern.compile("\\|\\|");
    private static final Pattern TERNARY = Pattern.compile("\\?(?!\\.)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inMethod = false;
        int braceCount = 0;
        int methodLine = -1;
        int complexity = 0;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (!inMethod && METHOD_DECL.matcher(line).find()) {
                inMethod = true;
                braceCount = 0;
                methodLine = i;
                complexity = 0;
            }

            if (!inMethod) continue;

            int nestingLevel = Math.max(0, braceCount - 1);

            braceCount += ApexContentHelper.countChar(line, '{');
            braceCount -= ApexContentHelper.countChar(line, '}');

            if (i == methodLine) continue;

            String trimmed = line.trim();

            if (ELSE_IF.matcher(trimmed).find()) {
                complexity += 1;
            } else if (ELSE_ONLY.matcher(trimmed).find()) {
                complexity += 1;
            } else {
                if (IF_KW.matcher(trimmed).find()) complexity += (1 + nestingLevel);
                if (FOR_KW.matcher(trimmed).find()) complexity += (1 + nestingLevel);
                if (WHILE_KW.matcher(trimmed).find()) complexity += (1 + nestingLevel);
                if (CATCH_KW.matcher(trimmed).find()) complexity += (1 + nestingLevel);
                if (SWITCH_KW.matcher(trimmed).find()) complexity += (1 + nestingLevel);
            }

            Matcher am = AND_OP.matcher(trimmed);
            while (am.find()) complexity++;

            Matcher om = OR_OP.matcher(trimmed);
            while (om.find()) complexity++;

            Matcher tm = TERNARY.matcher(trimmed);
            while (tm.find()) complexity += (1 + nestingLevel);

            if (braceCount <= 0 && i > methodLine) {
                if (complexity > DEFAULT_THRESHOLD) {
                    addIssue(methodLine + 1, "Cognitive complexity is " + complexity + " (max " + DEFAULT_THRESHOLD + ").");
                }
                inMethod = false;
            }
        }
    }
}
