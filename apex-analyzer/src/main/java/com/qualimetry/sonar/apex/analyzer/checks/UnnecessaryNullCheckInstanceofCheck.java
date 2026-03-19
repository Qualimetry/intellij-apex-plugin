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
 * Null checks before instanceof are redundant.
 */
@Rule(
        key = "qa-design-unnecessary-null-check-instanceof",
        name = "Null checks before instanceof are redundant",
        description = "The instanceof operator already returns false for null values, making a preceding null check redundant",
        tags = {"design", "unused"},
        priority = Priority.MINOR
)
public class UnnecessaryNullCheckInstanceofCheck extends BaseCheck {

    private static final Pattern NULL_AND_INSTANCEOF = Pattern.compile("!=\\s*null\\s*&&\\s*.*instanceof|==\\s*null\\s*\\|\\|\\s*.*instanceof");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (NULL_AND_INSTANCEOF.matcher(lines[i]).find()) {
                addIssue(i + 1, "Null checks before instanceof are redundant.");
            }
        }
    }
}
