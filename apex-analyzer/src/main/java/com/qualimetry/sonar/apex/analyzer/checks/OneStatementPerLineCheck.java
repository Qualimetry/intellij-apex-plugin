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
 * Each statement must appear on its own line.
 */
@Rule(
        key = "qa-convention-one-statement-per-line",
        name = "Each statement must appear on its own line",
        description = "Detects lines containing multiple statements separated by semicolons, reducing readability and debuggability.",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class OneStatementPerLineCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//") || line.trim().startsWith("*") || line.trim().isEmpty()) continue;
            String withoutStrings = line.replaceAll("\"[^\"]*\"", "\"\"");
            int semicolons = 0;
            for (int j = 0; j < withoutStrings.length(); j++) {
                if (withoutStrings.charAt(j) == ';') semicolons++;
            }
            if (semicolons > 1) {
                addIssue(i + 1, "Place each statement on its own line.");
            }
        }
    }
}
