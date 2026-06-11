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
 * if/else blocks must use curly braces.
 */
@Rule(
        key = "qa-convention-ifelse-braces-required",
        name = "if/else blocks must use curly braces",
        description = "Omitting braces around else blocks invites dangling-statement bugs when a second statement is added later",
        tags = {"convention", "misra"},
        priority = Priority.MAJOR
)
public class IfelseBracesRequiredCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            String trimmed = line.trim();
            if ((trimmed.startsWith("else ") || trimmed.equals("else")) && !line.contains("{")) {
                addIssue(i + 1, "if/else blocks must use curly braces.");
            } else if (trimmed.startsWith("else if ") && !line.contains("{")) {
                addIssue(i + 1, "if/else blocks must use curly braces.");
            }
        }
    }
}
