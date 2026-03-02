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
 * Assert methods must be called with correct argument types.
 */
@Rule(
        key = "qa-testing-correct-assert-usage",
        name = "Assert methods must be called with correct argument types",
        description = "Using the wrong assertion method produces misleading failure messages and obscures test intent",
        tags = {"performance", "testing"},
        priority = Priority.CRITICAL
)
public class CorrectAssertUsageCheck extends BaseCheck {

    private static final Pattern BOOL_FIRST_ARG = Pattern.compile(
            "(?i)Assert\\.(?:areEqual|areNotEqual)\\s*\\(\\s*(?:true|false)\\s*,");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (BOOL_FIRST_ARG.matcher(lines[i]).find()) {
                addIssue(i + 1, "Use Assert.isTrue or Assert.isFalse for boolean conditions instead of Assert.areEqual.");
            }
        }
    }
}
