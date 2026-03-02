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
 * Assert statements should only appear in test methods.
 */
@Rule(
        key = "qa-testing-no-asserts-in-production",
        name = "Assert statements should only appear in test methods",
        description = "Assertions in production code blur the boundary between test verification and runtime validation",
        tags = {"testing", "bad-practice"},
        priority = Priority.MAJOR
)
public class NoAssertsInProductionCheck extends BaseCheck {

    private static final Pattern IS_TEST = Pattern.compile("@IsTest|@isTest|@testMethod");
    private static final Pattern ASSERT = Pattern.compile("\\b(?:Assert\\.|System\\.assert)\\w*\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (IS_TEST.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (ASSERT.matcher(lines[i]).find()) {
                addIssue(i + 1, "Assert statements should only appear in test methods.");
            }
        }
    }
}
