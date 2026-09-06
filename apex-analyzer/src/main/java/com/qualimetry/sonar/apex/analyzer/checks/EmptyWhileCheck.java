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
 * While loop bodies must not be empty.
 */
@Rule(
        key = "qa-error-handling-empty-while",
        name = "While loop bodies must not be empty",
        description = "An empty while loop body either spins indefinitely consuming CPU or exits immediately, both of which indicate a bug",
        tags = {"unused", "suspicious"},
        priority = Priority.MINOR
)
public class EmptyWhileCheck extends BaseCheck {

    private static final Pattern WHILE_EMPTY = Pattern.compile("while\\s*\\([^)]+\\)\\s*\\{\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].replaceAll("//[^\n]*", "");
            if (WHILE_EMPTY.matcher(line).find()) {
                addIssue(i + 1, "While loop bodies must not be empty.");
            }
        }
    }
}
