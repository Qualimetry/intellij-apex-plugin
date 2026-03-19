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
 * Binary operators must not have identical expressions on both sides.
 */
@Rule(
        key = "qa-error-handling-identical-binary-operands",
        name = "Binary operators must not have identical expressions on both sides",
        description = "Detects binary expressions where the same operand appears on both sides, which always evaluates to a constant.",
        tags = {"bug", "suspicious"},
        priority = Priority.MAJOR
)
public class IdenticalBinaryOperandsCheck extends BaseCheck {

    private static final Pattern IDENTICAL_OP = Pattern.compile("(\\w+)\\s*(==|!=|<=|>=|<|>|\\+|\\-|\\*|/)\\s*\\1\\b");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].replaceAll("//[^\n]*", "");
            if (IDENTICAL_OP.matcher(line).find()) {
                addIssue(i + 1, "Binary operator must not have identical expressions on both sides.");
            }
        }
    }
}
