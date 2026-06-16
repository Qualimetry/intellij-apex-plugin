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
 * =+ and =- must not be used in place of += and -=.
 */
@Rule(
        key = "qa-error-handling-no-reversed-operator",
        name = "=+ and =- must not be used in place of += and -=",
        description = "Detects reversed compound operators like =+ and =- that should be written as += and -= respectively.",
        tags = {"bug", "suspicious"},
        priority = Priority.CRITICAL
)
public class NoReversedOperatorCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("=+") || line.contains("=-")) {
                addIssue(i + 1, "Use += or -= instead of =+ or =-.");
            }
        }
    }
}
