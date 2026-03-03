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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variable names must meet minimum length requirement (default 2).
 */
@Rule(
        key = "qa-convention-variable-name-min-length",
        name = "Variable names must meet minimum length requirement",
        description = "Single-letter or very short variable names provide no semantic information, making code harder to read and maintain",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class VariableNameMinLengthCheck extends BaseCheck {

    private static final int MIN_LENGTH = 2;
    private static final Pattern VARIABLE = Pattern.compile("\\b(?:Integer|String|Boolean|Object|List|Set|Map|\\w+)\\s+(\\w+)\\s*[;=,) ]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = VARIABLE.matcher(lines[i]);
            while (m.find()) {
                if (m.group(1).length() < MIN_LENGTH) {
                    addIssue(i + 1, "Variable name must be at least " + MIN_LENGTH + " characters.");
                    break;
                }
            }
        }
    }
}
