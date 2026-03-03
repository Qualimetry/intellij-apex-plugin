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
 * Closing brace placement must be consistent.
 */
@Rule(
        key = "qa-convention-right-brace-position",
        name = "Closing brace placement must be consistent",
        description = "Closing braces on the same line as statements make block boundaries easy to overlook, reducing readability",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class RightBracePositionCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("}") && line.contains(";") && line.trim().length() > 2 && !line.trim().startsWith("//")) {
                addIssue(i + 1, "Closing brace placement must be consistent (put '}' on its own line).");
                break;
            }
        }
    }
}
