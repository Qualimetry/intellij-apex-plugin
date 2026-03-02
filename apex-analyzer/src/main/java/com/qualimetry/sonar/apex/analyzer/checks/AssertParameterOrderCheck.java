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
 * Assertion parameters must follow expected-then-actual order.
 */
@Rule(
        key = "qa-testing-assert-parameter-order",
        name = "Assertion parameters must follow expected-then-actual order",
        description = "Detects assertEquals/assertNotEquals calls where expected and actual arguments are likely reversed.",
        tags = {"testing", "bug"},
        priority = Priority.MAJOR
)
public class AssertParameterOrderCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if ((line.contains("assertEquals") || line.contains("assertNotEquals"))
                    && (line.contains("(actual") || line.contains(", expected"))) {
                addIssue(i + 1, "Assertion parameters should be in expected-then-actual order.");
            }
        }
    }
}
