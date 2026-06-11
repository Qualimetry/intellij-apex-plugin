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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Empty method bodies must include a justifying comment.
 */
@Rule(
        key = "qa-design-empty-method-comment",
        name = "Empty method bodies must include a justifying comment",
        description = "Detects empty method bodies that lack a comment explaining why they are intentionally empty.",
        tags = {"design", "convention"},
        priority = Priority.MAJOR
)
public class EmptyMethodCommentCheck extends BaseCheck {

    private static final Pattern EMPTY_BODY = Pattern.compile("\\{\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.trim().matches("\\{\\s*}") || line.matches(".*\\{\\s*\\}.*")) {
                boolean hasComment = i > 0 && (lines[i - 1].trim().startsWith("//") || lines[i - 1].trim().startsWith("*"));
                if (!hasComment) {
                    addIssue(i + 1, "Empty method body must include a justifying comment.");
                }
            }
            if (i > 0 && line.equals("}") && lines[i - 1].trim().equals("{")) {
                boolean hasComment = i > 1 && (lines[i - 2].trim().startsWith("//") || lines[i - 2].trim().startsWith("*"));
                if (!hasComment) {
                    addIssue(i, "Empty method body must include a justifying comment.");
                }
            }
        }
    }
}
