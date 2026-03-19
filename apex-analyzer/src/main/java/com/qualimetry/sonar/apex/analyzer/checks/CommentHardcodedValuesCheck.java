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

/**
 * Hardcoded values should have explanatory comments.
 */
@Rule(
        key = "qa-convention-comment-hardcoded-values",
        name = "Hardcoded values should have explanatory comments",
        description = "Unexplained magic numbers force maintainers to guess their purpose, increasing the risk of incorrect modifications",
        tags = {"bad-practice"},
        priority = Priority.MINOR
)
public class CommentHardcodedValuesCheck extends BaseCheck {

    private static final java.util.regex.Pattern MAGIC_NUMBER = java.util.regex.Pattern.compile("\\b(\\d{2,})\\b");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            if (MAGIC_NUMBER.matcher(line).find() && (i == 0 || !lines[i - 1].trim().startsWith("//"))) {
                addIssue(i + 1, "Hardcoded values should have explanatory comments.");
                break;
            }
        }
    }
}
