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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Pattern;

/**
 * Prefer the null coalescing operator over ternary null checks.
 */
@Rule(
        key = "qa-design-prefer-null-coalescing",
        name = "Prefer the null coalescing operator over ternary null checks",
        description = "The null coalescing operator (??) is more concise and readable than a ternary that checks for null and provides a default",
        tags = {"convention", "performance"},
        priority = Priority.MAJOR
)
public class PreferNullCoalescingCheck extends BaseCheck {

    private static final Pattern TERNARY_NULL = Pattern.compile("\\?\\s*[^:?]+\\s*:\\s*[^;?]+\\s*;");
    private static final Pattern NULL_TERNARY = Pattern.compile("\\w+\\s*==\\s*null\\s*\\?");
    private static final Pattern COALESCE = Pattern.compile("\\?\\s*:");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (NULL_TERNARY.matcher(lines[i]).find() && !COALESCE.matcher(lines[i]).find()
                    && !lines[i].trim().startsWith("//")) {
                addIssue(i + 1, "Prefer the null coalescing operator over ternary null checks.");
            }
        }
    }
}
