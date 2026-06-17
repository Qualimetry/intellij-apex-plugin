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
 * Source lines must not exceed max character length.
 */
@Rule(
        key = "qa-convention-line-length-limit",
        name = "Source lines must not exceed max character length",
        description = "Detects source lines that exceed the configured maximum character length, reducing readability.",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class LineLengthLimitCheck extends BaseCheck {

    private static final int MAX_LENGTH = 120;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            int len = lines[i].length();
            if (len > MAX_LENGTH) {
                addIssue(i + 1, "Line has " + len + " characters (max " + MAX_LENGTH + ").");
            }
        }
    }
}
