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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Multi-line comment blocks must not be empty.
 */
@Rule(
        key = "qa-convention-no-empty-comment",
        name = "Multi-line comment blocks must not be empty",
        description = "Detects multi-line comment blocks with no content, which add noise without providing information.",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class NoEmptyCommentCheck extends BaseCheck {

    private static final Pattern BLOCK_COMMENT = Pattern.compile("/\\*([\\s\\S]*?)\\*/");

    @Override
    public void scan(InputFile file, String content) {
        Matcher m = BLOCK_COMMENT.matcher(content);
        while (m.find()) {
            String inner = m.group(1).trim().replaceAll("\\s", "").replaceAll("\\*", "");
            if (inner.isEmpty()) {
                int line = lineAtOffset(content, m.start());
                addIssue(line, "Multi-line comment block must not be empty.");
            }
        }
    }

    private static int lineAtOffset(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
