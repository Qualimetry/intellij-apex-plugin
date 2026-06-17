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
 * Detects broken null-check patterns where a variable is dereferenced before
 * its null guard, or where {@code ||} allows the dereference to execute even
 * when the variable is null.
 */
@Rule(
        key = "qa-error-handling-broken-null-check",
        name = "Null checks must be correctly structured to prevent NPE",
        description = "A null check that uses the wrong logical operator or appears after the variable is already dereferenced fails to prevent NullPointerException",
        tags = {"pitfall"},
        priority = Priority.CRITICAL
)
public class BrokenNullCheckCheck extends BaseCheck {

    // x.something ... && ... x != null  —  dereference happens before null guard
    private static final Pattern DEREF_BEFORE_NULL = Pattern.compile(
            "(\\w+)\\.\\w+.*&&\\s*\\1\\s*!=\\s*null");

    // x != null || ... x.something  —  || means RHS still executes when x IS null
    private static final Pattern NULL_OR_DEREF = Pattern.compile(
            "(\\w+)\\s*!=\\s*null\\s*\\|\\|.*\\1\\.");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (DEREF_BEFORE_NULL.matcher(line).find() || NULL_OR_DEREF.matcher(line).find()) {
                addIssue(i + 1, "Null checks must be correctly structured to prevent NPE.");
            }
        }
    }
}
