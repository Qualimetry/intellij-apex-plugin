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
 * Throwable should not be caught directly.
 */
@Rule(
        key = "qa-error-handling-no-catch-throwable",
        name = "Throwable should not be caught directly",
        description = "Catching Throwable intercepts system-level errors that should be allowed to propagate, hiding critical failures",
        tags = {"cwe", "error-handling"},
        priority = Priority.MAJOR
)
public class NoCatchThrowableCheck extends BaseCheck {

    private static final Pattern CATCH_THROWABLE = Pattern.compile("\\bcatch\\s*\\([^)]*Throwable[^)]*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (CATCH_THROWABLE.matcher(lines[i]).find()) {
                addIssue(i + 1, "Throwable should not be caught directly.");
            }
        }
    }
}
