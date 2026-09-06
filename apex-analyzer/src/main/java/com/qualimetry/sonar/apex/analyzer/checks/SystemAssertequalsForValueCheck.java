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
 * Use System.assertEquals for value equality verification.
 */
@Rule(
        key = "qa-testing-system-assertequals-for-value",
        name = "Use System.assertEquals for value equality",
        description = "System.assert(a == b) hides actual values on failure; assertEquals shows both for easier debugging",
        tags = {"testing", "bad-practice"},
        priority = Priority.MINOR
)
public class SystemAssertequalsForValueCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("System.assert(") && line.contains("==") && !line.contains("System.assertEquals")) {
                addIssue(i + 1, "Use System.assertEquals(expected, actual) for value comparison.");
            }
        }
    }
}
