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
 * Null checks must appear before the variable is dereferenced.
 */
@Rule(
        key = "qa-error-handling-misplaced-null-check",
        name = "Null checks must appear before the variable is dereferenced",
        description = "Checking a variable for null after it has already been dereferenced is too late to prevent a NullPointerException",
        tags = {"pitfall"},
        priority = Priority.MAJOR
)
public class MisplacedNullCheckCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            boolean hasNullCheck = line.contains("== null") || line.contains("null ==");
            boolean hasDeref = Pattern.compile("\\w+\\.\\w+").matcher(line).find();
            if (hasNullCheck && hasDeref) {
                addIssue(i + 1, "Null checks must appear before the variable is dereferenced.");
            }
        }
    }
}
