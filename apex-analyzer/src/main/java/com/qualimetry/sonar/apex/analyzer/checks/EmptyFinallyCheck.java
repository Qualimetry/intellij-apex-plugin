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
 * Finally blocks must contain at least one statement.
 */
@Rule(
        key = "qa-error-handling-empty-finally",
        name = "Finally blocks must contain at least one statement",
        description = "An empty finally block serves no purpose and suggests incomplete cleanup logic that was forgotten during development",
        tags = {"unused"},
        priority = Priority.MINOR
)
public class EmptyFinallyCheck extends BaseCheck {

    private static final Pattern FINALLY_EMPTY = Pattern.compile("finally\\s*\\{\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (FINALLY_EMPTY.matcher(lines[i].replaceAll("//[^\n]*", "")).find()) {
                addIssue(i + 1, "Finally blocks must contain at least one statement.");
            }
        }
    }
}
