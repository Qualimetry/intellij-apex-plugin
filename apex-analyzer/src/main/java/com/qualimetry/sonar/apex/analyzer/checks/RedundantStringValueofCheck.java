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
 * Redundant String.valueOf() calls should be removed.
 */
@Rule(
        key = "qa-design-redundant-string-valueof",
        name = "Redundant String.valueOf() calls should be removed",
        description = "Calling String.valueOf() on an expression that is already a String is redundant and should be simplified",
        tags = {"clumsy"},
        priority = Priority.MINOR
)
public class RedundantStringValueofCheck extends BaseCheck {

    private static final Pattern STRING_VALUEOF_STRING = Pattern.compile("String\\.valueOf\\s*\\([^)]*String[^)]*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (STRING_VALUEOF_STRING.matcher(lines[i]).find()) {
                addIssue(i + 1, "Redundant String.valueOf() calls should be removed.");
            }
        }
    }
}
