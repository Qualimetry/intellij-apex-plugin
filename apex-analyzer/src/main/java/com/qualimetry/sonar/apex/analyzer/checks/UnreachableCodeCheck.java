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
 * All code paths must be reachable.
 */
@Rule(
        key = "qa-error-handling-unreachable-code",
        name = "All code paths must be reachable",
        description = "Detects code placed after return, throw, or break statements that can never be executed.",
        tags = {"bug", "unused"},
        priority = Priority.MAJOR
)
public class UnreachableCodeCheck extends BaseCheck {

    private static final Pattern TERMINATOR = Pattern.compile(
            "^\\s*(?:return\\b.*|throw\\b.*|break\\s*;|continue\\s*;)\\s*$");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (TERMINATOR.matcher(lines[i]).find() && i + 1 < lines.length) {
                String next = lines[i + 1].trim();
                if (!next.isEmpty() && !next.startsWith("}") && !next.startsWith("catch") && !next.startsWith("finally")) {
                    addIssue(i + 2, "Unreachable code after return/throw/break/continue.");
                }
            }
        }
    }
}
