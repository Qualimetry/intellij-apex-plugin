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
 * Consecutive unary operators create confusing expressions.
 */
@Rule(
        key = "qa-error-handling-no-multiple-unary",
        name = "Consecutive unary operators create confusing expressions",
        description = "Stacking multiple unary operators (e.g., !!x or --i) produces hard-to-read expressions that are prone to logic errors",
        tags = {"misra", "cert"},
        priority = Priority.CRITICAL
)
public class NoMultipleUnaryCheck extends BaseCheck {

    private static final Pattern DOUBLE_NOT = Pattern.compile("!!");
    private static final Pattern DOUBLE_MINUS = Pattern.compile("--(?!\\s*[;,)\\]])");
    private static final Pattern DOUBLE_PLUS = Pattern.compile("\\+\\+(?!\\s*[;,)\\]])");

    @Override
    public void scan(InputFile file, String content) {
        String noStrings = content.replaceAll("'[^']*'", "''").replaceAll("\"[^\"]*\"", "\"\"");
        String[] lines = noStrings.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (DOUBLE_NOT.matcher(line).find()
                    || (DOUBLE_MINUS.matcher(line).find() && !line.trim().startsWith("--"))
                    || (DOUBLE_PLUS.matcher(line).find() && !line.trim().startsWith("++"))) {
                addIssue(i + 1, "Consecutive unary operators create confusing expressions.");
            }
        }
    }
}
