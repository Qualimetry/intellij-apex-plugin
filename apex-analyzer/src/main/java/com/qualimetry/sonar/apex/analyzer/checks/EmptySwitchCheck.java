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
 * Switch blocks must contain at least one case.
 */
@Rule(
        key = "qa-error-handling-empty-switch",
        name = "Switch blocks must contain at least one case",
        description = "A switch statement with no when clauses evaluates its expression but takes no action, indicating forgotten implementation",
        tags = {"unused", "suspicious"},
        priority = Priority.MINOR
)
public class EmptySwitchCheck extends BaseCheck {

    private static final Pattern SWITCH_EMPTY = Pattern.compile("switch\\s*\\([^)]+\\)\\s*\\{\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].replaceAll("//[^\n]*", "");
            if (SWITCH_EMPTY.matcher(line).find()) {
                addIssue(i + 1, "Switch blocks must contain at least one case.");
            }
        }
    }
}
