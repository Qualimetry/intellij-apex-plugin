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
 * For loop bodies must be enclosed in curly braces.
 */
@Rule(
        key = "qa-convention-for-braces-required",
        name = "For loop bodies must be enclosed in curly braces",
        description = "Detects for loop bodies not enclosed in curly braces, which risk unintended behavior when code is added.",
        tags = {"convention", "misra"},
        priority = Priority.MAJOR
)
public class ForBracesRequiredCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (trimmed.startsWith("//") || trimmed.startsWith("*")) continue;
            if (line.matches(".*\\bfor\\s*\\([^)]*\\)\\s*[^\\s{;].*")) {
                addIssue(i + 1, "For loop body must be enclosed in curly braces.");
            } else if (line.matches(".*\\bfor\\s*\\([^)]*\\)\\s*")) {
                String nextTrimmed = (i + 1 < lines.length) ? lines[i + 1].trim() : "";
                if (!nextTrimmed.isEmpty() && !nextTrimmed.startsWith("{")) {
                    addIssue(i + 1, "For loop body must be enclosed in curly braces.");
                }
            }
        }
    }
}
