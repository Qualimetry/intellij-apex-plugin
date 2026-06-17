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
 * Opening brace placement must be consistent (e.g. same line or next line).
 */
@Rule(
        key = "qa-convention-left-brace-position",
        name = "Opening brace placement must be consistent",
        description = "Mixing K&R and Allman brace styles in the same file creates visual inconsistency that distracts from the logic during reviews",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class LeftBracePositionCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean seenEndOfLine = false;
        boolean seenNextLine = false;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("{")) {
                if (line.trim().equals("{")) seenNextLine = true;
                else if (line.trim().endsWith("{")) seenEndOfLine = true;
            }
        }
        if (seenEndOfLine && seenNextLine) {
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].trim().equals("{")) {
                    addIssue(i + 1, "Opening brace placement must be consistent.");
                    break;
                }
            }
        }
    }
}
