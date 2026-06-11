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
 * Return must not use redundant boolean literals.
 */
@Rule(
        key = "qa-design-simplify-boolean-return",
        name = "Return must not use redundant boolean literals",
        description = "Detects if/else blocks that return true/false literals and can be replaced with a direct boolean return.",
        tags = {"design", "clumsy"},
        priority = Priority.MINOR
)
public class SimplifyBooleanReturnCheck extends BaseCheck {

    private static final Pattern TERNARY_TRUE_FALSE = Pattern.compile(
            "return\\s+.+\\?\\s*true\\s*:\\s*false\\s*;");
    private static final Pattern TERNARY_FALSE_TRUE = Pattern.compile(
            "return\\s+.+\\?\\s*false\\s*:\\s*true\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (TERNARY_TRUE_FALSE.matcher(line).matches()) {
                addIssue(i + 1, "Simplify boolean return: use the condition directly.");
            } else if (TERNARY_FALSE_TRUE.matcher(line).matches()) {
                addIssue(i + 1, "Simplify boolean return: use the negated condition directly.");
            }
        }
        detectMultiLinePattern(lines);
    }

    private void detectMultiLinePattern(String[] lines) {
        for (int i = 0; i < lines.length - 4; i++) {
            String l0 = lines[i].trim();
            String l1 = lines[i + 1].trim();
            String l2 = lines[i + 2].trim();
            String l3 = lines[i + 3].trim();
            String l4 = lines[i + 4].trim();
            if (l0.matches("(?i)if\\s*\\(.*\\)\\s*\\{")
                    && l1.matches("return\\s+true\\s*;")
                    && l2.matches("\\}\\s*else\\s*\\{")
                    && l3.matches("return\\s+false\\s*;")
                    && l4.equals("}")) {
                addIssue(i + 1, "Simplify boolean return: use the condition directly.");
            } else if (l0.matches("(?i)if\\s*\\(.*\\)\\s*\\{")
                    && l1.matches("return\\s+false\\s*;")
                    && l2.matches("\\}\\s*else\\s*\\{")
                    && l3.matches("return\\s+true\\s*;")
                    && l4.equals("}")) {
                addIssue(i + 1, "Simplify boolean return: use the negated condition directly.");
            }
        }
    }
}
