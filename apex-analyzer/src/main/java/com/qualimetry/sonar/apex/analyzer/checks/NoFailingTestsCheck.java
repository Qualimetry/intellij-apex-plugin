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

/**
 * All unit test methods must pass.
 */
@Rule(
        key = "qa-testing-no-failing-tests",
        name = "All unit test methods must pass",
        description = "Failing tests indicate broken functionality, erode test suite confidence, and block deployments",
        tags = {"testing"},
        priority = Priority.MAJOR
)
public class NoFailingTestsCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("@IsTest") && !content.contains("@testMethod")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("Assert.areEqual(false") || line.contains("Assert.areEqual( false") || line.contains("System.assert(false)") || line.contains("Assert.isTrue(false)")) {
                addIssue(i + 1, "Test appears to always fail; fix or remove.");
            }
        }
    }
}
