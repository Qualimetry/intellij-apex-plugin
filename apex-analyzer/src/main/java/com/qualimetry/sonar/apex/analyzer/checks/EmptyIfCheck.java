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
 * If blocks must contain at least one statement.
 */
@Rule(
        key = "qa-error-handling-empty-if",
        name = "If blocks must contain at least one statement",
        description = "An empty if block evaluates a condition but takes no action, indicating either missing logic or dead code",
        tags = {"unused", "suspicious"},
        priority = Priority.MINOR
)
public class EmptyIfCheck extends BaseCheck {

    private static final Pattern IF_EMPTY = Pattern.compile("\\bif\\s*\\([^)]+\\)\\s*\\{\\s*\\}");
    private static final Pattern IF_OPEN = Pattern.compile("\\bif\\s*\\([^)]*\\)\\s*\\{\\s*$");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String trimmed = lines[i].trim();
            if (IF_EMPTY.matcher(trimmed).find()) {
                addIssue(i + 1, "If blocks must contain at least one statement.");
                continue;
            }
            if (IF_OPEN.matcher(trimmed).find()) {
                for (int j = i + 1; j < lines.length; j++) {
                    String next = lines[j].trim();
                    if (next.isEmpty()) continue;
                    if (next.equals("}")) {
                        addIssue(i + 1, "If blocks must contain at least one statement.");
                    }
                    break;
                }
            }
        }
    }
}
