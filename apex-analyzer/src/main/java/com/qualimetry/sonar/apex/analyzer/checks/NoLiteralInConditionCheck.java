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
 * Conditional statements should not use literal values (use named constants).
 */
@Rule(
        key = "qa-convention-no-literal-in-condition",
        name = "Conditional statements should not use literal values",
        description = "Magic numbers and string literals in conditions obscure intent and force changes in multiple places when the value needs updating",
        tags = {"convention", "bad-practice"},
        priority = Priority.MINOR
)
public class NoLiteralInConditionCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = content.replace("\r", "").split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!line.contains("if") || line.trim().startsWith("//")) continue;
            if ((line.contains("==") || line.contains("!=") || line.contains(">") || line.contains("<"))
                    && (line.contains("'") || line.contains("\"") || line.matches(".*\\b\\d+\\b.*"))) {
                addIssue(i + 1, "Conditional statements should not use literal values; use named constants.");
            }
        }
    }
}
