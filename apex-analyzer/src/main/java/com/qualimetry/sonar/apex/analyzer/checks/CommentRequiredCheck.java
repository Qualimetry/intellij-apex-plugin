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
 * Public members must include documentation comments.
 */
@Rule(
        key = "qa-convention-comment-required",
        name = "Public members must include documentation comments",
        description = "Undocumented public members force consumers to read the implementation to understand behavior, slowing onboarding and code reviews",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class CommentRequiredCheck extends BaseCheck {

    private static final Pattern PUBLIC_METHOD = Pattern.compile("(?:public|global)\\s+(?:static\\s+)?(?:override\\s+)?(?:\\w+\\s+)?\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (PUBLIC_METHOD.matcher(lines[i]).find()) {
                boolean hasComment = false;
                for (int j = i - 1; j >= 0 && j >= i - 5; j--) {
                    String prev = lines[j].trim();
                    if (prev.startsWith("//") || prev.startsWith("/**") || prev.startsWith("*")) {
                        hasComment = true;
                        break;
                    }
                    if (!prev.isEmpty() && !prev.startsWith("//") && !prev.startsWith("*")) break;
                }
                if (!hasComment) {
                    addIssue(i + 1, "Public members must include documentation comments.");
                    break;
                }
            }
        }
    }
}
