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

import java.util.regex.Pattern;

/**
 * Avoid explicitly assigning null to variables.
 */
@Rule(
        key = "qa-design-no-null-assignment",
        name = "Avoid explicitly assigning null to variables",
        description = "Explicit null assignments increase the risk of NullPointerException and can often be avoided with empty collections, default values, or the Null Object pattern",
        tags = {"convention", "clumsy"},
        priority = Priority.MAJOR
)
public class NoNullAssignmentCheck extends BaseCheck {

    private static final Pattern NULL_ASSIGN = Pattern.compile("(?<![=!<>])=\\s*null\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (NULL_ASSIGN.matcher(lines[i]).find()) {
                addIssue(i + 1, "Avoid explicitly assigning null to variables.");
            }
        }
    }
}
