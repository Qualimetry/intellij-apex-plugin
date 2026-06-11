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
 * Boolean expressions should not be unnecessarily inverted.
 */
@Rule(
        key = "qa-design-no-inverted-boolean",
        name = "Boolean expressions should not be unnecessarily inverted",
        description = "Detects unnecessarily inverted boolean expressions that can be simplified for clarity.",
        tags = {"design", "clumsy"},
        priority = Priority.MINOR
)
public class NoInvertedBooleanCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//") || line.trim().startsWith("*")) continue;
            if (line.contains("return ") && line.contains("!") && line.contains(";") && !line.contains("?")) {
                if (line.replaceAll("\\s", " ").matches(".*return\\s+!\\s*\\w+\\s*;.*")) {
                    addIssue(i + 1, "Invert the boolean logic instead of returning negation.");
                }
            }
        }
    }
}
