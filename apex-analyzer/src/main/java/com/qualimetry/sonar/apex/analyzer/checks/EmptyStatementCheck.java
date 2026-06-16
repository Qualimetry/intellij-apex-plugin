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
 * Standalone empty statements should be removed.
 */
@Rule(
        key = "qa-error-handling-empty-statement",
        name = "Standalone empty statements should be removed",
        description = "A stray semicolon creates an empty statement that has no effect and is likely a typo or leftover from refactoring",
        tags = {"misra", "unused"},
        priority = Priority.MINOR
)
public class EmptyStatementCheck extends BaseCheck {

    private static final Pattern STANDALONE_SEMICOLON = Pattern.compile("^\\s*;\\s*$");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (STANDALONE_SEMICOLON.matcher(lines[i].trim()).find()) {
                addIssue(i + 1, "Standalone empty statements should be removed.");
            }
        }
    }
}
