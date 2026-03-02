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
 * Remove parentheses that do not affect evaluation order.
 */
@Rule(
        key = "qa-convention-no-useless-parens",
        name = "Remove parentheses that do not affect evaluation order",
        description = "Detects parentheses in return statements and expressions that do not affect evaluation and can be removed.",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class NoUselessParensCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//") || line.trim().startsWith("*")) continue;
            String lineNorm = line.replaceAll("\\s+", " ").trim();
            if (lineNorm.contains("return (") && lineNorm.contains(");") && lineNorm.matches(".*return\\s+\\(\\s*[a-zA-Z_][a-zA-Z0-9_]*\\s*\\)\\s*;.*")) {
                addIssue(i + 1, "Remove useless parentheses around identifier.");
            }
        }
    }
}
