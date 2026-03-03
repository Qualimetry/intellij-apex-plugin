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
 * Source files must have adequate comment density (minimum ratio of comment lines to code lines).
 */
@Rule(
        key = "qa-convention-comment-density",
        name = "Source files must have adequate comment density",
        description = "Files with insufficient comments become difficult for new team members to understand, increasing onboarding time and maintenance cost",
        tags = {"convention"},
        priority = Priority.MAJOR
)
public class CommentDensityCheck extends BaseCheck {

    private static final double MIN_RATIO = 0.1;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        int codeLines = 0;
        int commentLines = 0;
        for (String line : lines) {
            String t = line.trim();
            if (t.isEmpty()) continue;
            if (t.startsWith("//") || t.startsWith("/*") || t.startsWith("*") || t.startsWith("*/")) {
                commentLines++;
            } else {
                codeLines++;
            }
        }
        int total = commentLines + codeLines;
        if (total > 0 && codeLines > 5) {
            double ratio = (double) commentLines / total;
            if (ratio < MIN_RATIO) {
                addIssue(1, "Source files must have adequate comment density (add documentation comments).");
            }
        }
    }
}
