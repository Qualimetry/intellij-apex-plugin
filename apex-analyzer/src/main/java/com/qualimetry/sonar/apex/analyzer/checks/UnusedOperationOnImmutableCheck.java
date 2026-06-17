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

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Operations on immutable values have no effect when the return value is discarded.
 */
@Rule(
        key = "qa-unused-operation-on-immutable",
        name = "Operations on immutable values have no effect",
        description = "Methods like String.trim() return new values; ignoring the return value is always a bug",
        tags = {"clumsy"},
        priority = Priority.CRITICAL
)
public class UnusedOperationOnImmutableCheck extends BaseCheck {

    private static final Set<String> IMMUTABLE_METHODS = Set.of(
            "trim", "replace", "replaceAll", "toLowerCase", "toUpperCase",
            "substring", "capitalize", "uncapitalize", "abbreviate", "center",
            "leftPad", "rightPad", "repeat", "reverse", "stripHtmlTags",
            "normalizeSpace", "addDays", "addMonths", "addYears",
            "addHours", "addMinutes", "addSeconds"
    );

    private static final Pattern CALL_PATTERN = Pattern.compile(
            "\\w+\\.(\\w+)\\s*\\(");
    private static final Pattern DISCARDED_CALL = Pattern.compile(
            "^\\s*\\w+\\.\\w+\\s*\\(.*\\)\\s*;\\s*$");
    private static final Pattern ASSIGNED_OR_RETURNED = Pattern.compile(
            "^\\s*(?:\\w+\\s*=|return\\s)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!DISCARDED_CALL.matcher(line).matches()) continue;
            if (ASSIGNED_OR_RETURNED.matcher(line).find()) continue;

            Matcher m = CALL_PATTERN.matcher(line);
            if (m.find() && IMMUTABLE_METHODS.contains(m.group(1))) {
                addIssue(i + 1, "Return value of '" + m.group(1) + "()' is discarded; operation has no effect on immutable type.");
            }
        }
    }
}
