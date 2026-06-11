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

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Comments must not contain prohibited words or phrases (e.g. TODO, FIXME, HACK).
 */
@Rule(
        key = "qa-convention-no-invalid-comment-words",
        name = "Comments must not contain prohibited words or phrases",
        description = "Prohibited markers like TODO and FIXME left in production indicate unfinished work and accumulate as ignored technical debt",
        tags = {"convention"},
        priority = Priority.MAJOR
)
public class NoInvalidCommentWordsCheck extends BaseCheck {

    private static final Set<String> PROHIBITED = Set.of("TODO", "FIXME", "HACK", "XXX");
    private static final Pattern COMMENT = Pattern.compile("//.*|/\\*[^*]*\\*/|\\*[^*]*");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!line.trim().startsWith("//") && !line.contains("/*") && !line.contains("*")) continue;
            String upper = line.toUpperCase();
            for (String word : PROHIBITED) {
                if (upper.contains(word)) {
                    addIssue(i + 1, "Comments must not contain prohibited word or phrase: " + word + ".");
                    break;
                }
            }
        }
    }
}
