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
 * Do not use == null where .equals() is intended.
 */
@Rule(
        key = "qa-error-handling-equals-null",
        name = "Do not use == null where .equals() is intended",
        description = "Comparing with == null tests reference identity, not equality; use .equals() for value comparison or explicit null checks",
        tags = {"unused", "cert"},
        priority = Priority.BLOCKER
)
public class EqualsNullCheck extends BaseCheck {

    private static final Pattern LITERAL_EQUALS_NULL = Pattern.compile("\\.equals\\s*\\(\\s*null\\s*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (LITERAL_EQUALS_NULL.matcher(line).find()) {
                addIssue(i + 1, "Do not use .equals(null); use == null or != null for null checks.");
            }
        }
    }
}
