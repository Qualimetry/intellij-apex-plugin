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
 * if blocks must be enclosed in curly braces.
 */
@Rule(
        key = "qa-convention-if-braces-required",
        name = "if blocks must be enclosed in curly braces",
        description = "Single-statement if bodies without braces are fragile and easily introduce bugs when a second statement is added",
        tags = {"convention", "misra"},
        priority = Priority.MAJOR
)
public class IfBracesRequiredCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = content.replace("\r", "").split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.matches(".*\\bif\\s*\\([^)]+\\)\\s*[^{;].*") && !line.trim().startsWith("//")) {
                if (!line.contains("{") && (line.contains(";") || (i + 1 < lines.length && !lines[i + 1].trim().startsWith("{")))) {
                    addIssue(i + 1, "if blocks must be enclosed in curly braces.");
                }
            }
        }
    }
}
