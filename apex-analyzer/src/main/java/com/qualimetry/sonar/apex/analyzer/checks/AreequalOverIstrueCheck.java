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
 * Prefer Assert.areEqual over Assert.isTrue for equality checks.
 */
@Rule(
        key = "qa-testing-areequal-over-istrue",
        name = "Prefer Assert.areEqual over Assert.isTrue for equality",
        description = "Assert.isTrue(a == b) hides the compared values on failure; areEqual displays both for easier debugging",
        tags = {"testing", "bad-practice"},
        priority = Priority.MINOR
)
public class AreequalOverIstrueCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("Assert.isTrue(") && line.contains("==") && !line.contains("boolean")) {
                addIssue(i + 1, "Use Assert.areEqual instead of Assert.isTrue for equality.");
            }
        }
    }
}
