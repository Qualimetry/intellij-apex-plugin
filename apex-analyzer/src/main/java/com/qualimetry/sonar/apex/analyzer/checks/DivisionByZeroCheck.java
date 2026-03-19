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
 * Guard against potential division by zero.
 */
@Rule(
        key = "qa-error-handling-division-by-zero",
        name = "Guard against potential division by zero",
        description = "Dividing by a variable that could be zero causes an unrecoverable MathException at runtime; always validate the denominator first",
        tags = {"cwe", "cert"},
        priority = Priority.MAJOR
)
public class DivisionByZeroCheck extends BaseCheck {

    private static final Pattern DIV_OR_MOD_VAR = Pattern.compile("[/%]\\s*[a-zA-Z_]\\w*");

    @Override
    public void scan(InputFile file, String content) {
        String noStrings = content.replaceAll("'[^']*'", "''").replaceAll("\"[^\"]*\"", "\"\"");
        String[] lines = noStrings.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            if (DIV_OR_MOD_VAR.matcher(line).find()) {
                addIssue(i + 1, "Guard against potential division by zero.");
            }
        }
    }
}
