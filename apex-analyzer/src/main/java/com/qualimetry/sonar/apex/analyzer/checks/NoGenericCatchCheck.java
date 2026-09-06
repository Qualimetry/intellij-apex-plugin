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
 * Catch specific exception types rather than generic Exception.
 */
@Rule(
        key = "qa-error-handling-no-generic-catch",
        name = "Catch specific exception types rather than generic Exception",
        description = "Detects catch blocks that catch generic Exception instead of specific exception types, masking different failure modes.",
        tags = {"error-handling", "cert"},
        priority = Priority.MAJOR
)
public class NoGenericCatchCheck extends BaseCheck {

    private static final Pattern CATCH_EXCEPTION = Pattern.compile("catch\\s*\\(\\s*Exception\\s+\\w*\\s*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (CATCH_EXCEPTION.matcher(lines[i]).find()) {
                addIssue(i + 1, "Catch specific exception types rather than generic Exception.");
            }
        }
    }
}
