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
 * Production code should not branch on Test.isRunningTest().
 */
@Rule(
        key = "qa-testing-no-isrunningtest",
        name = "Production code should not branch on Test.isRunningTest()",
        description = "Branching on Test.isRunningTest() creates divergent paths that invalidate tests by skipping production logic",
        tags = {"salesforce", "testing"},
        priority = Priority.MINOR
)
public class NoIsrunningtestCheck extends BaseCheck {

    private static final Pattern IS_RUNNING_TEST = Pattern.compile("Test\\.isRunningTest\\(\\)|isRunningTest\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!IS_RUNNING_TEST.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (IS_RUNNING_TEST.matcher(lines[i]).find()) {
                addIssue(i + 1, "Do not branch on Test.isRunningTest() in production code.");
            }
        }
    }
}
