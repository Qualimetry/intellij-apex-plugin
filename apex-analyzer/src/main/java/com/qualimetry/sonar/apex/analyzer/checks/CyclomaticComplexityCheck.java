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
 * Method cyclomatic complexity must not exceed threshold.
 */
@Rule(
        key = "qa-complexity-cyclomatic",
        name = "Method cyclomatic complexity must not exceed threshold",
        description = "High cyclomatic complexity means many independent paths, each requiring a separate test case to cover",
        tags = {"brain-overload"},
        priority = Priority.CRITICAL
)
public class CyclomaticComplexityCheck extends BaseCheck {

    private static final int DEFAULT_THRESHOLD = 10;

    private static final Pattern METHOD_DECL = Pattern.compile(
            "(?i)\\b\\w+\\s+" +
            "(?!if\\b|else\\b|for\\b|while\\b|switch\\b|catch\\b|try\\b|do\\b|new\\b|class\\b|return\\b)" +
            "\\w+\\s*\\([^)]*\\)\\s*\\{");
    private static final Pattern DECISION_KEYWORD = Pattern.compile(
            "\\b(?:if|for|while|catch|case|when)\\b");
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
                complexity = 1;
            }

            if (!inMethod) continue;

            braceCount += ApexContentHelper.countChar(line, '{');
            braceCount -= ApexContentHelper.countChar(line, '}');

            Matcher dm = DECISION_KEYWORD.matcher(line);
            while (dm.find()) complexity++;

            Matcher am = AND_OP.matcher(line);
            while (am.find()) complexity++;

            Matcher om = OR_OP.matcher(line);
            while (om.find()) complexity++;

            Matcher tm = TERNARY.matcher(line);
            while (tm.find()) complexity++;

            if (braceCount <= 0 && i > methodLine) {
                if (complexity > DEFAULT_THRESHOLD) {
                    addIssue(methodLine + 1, "Cyclomatic complexity is " + complexity + " (max " + DEFAULT_THRESHOLD + ").");
                }
                inMethod = false;
            }
        }
    }
}
