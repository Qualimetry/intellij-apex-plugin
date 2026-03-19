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
 * Do not embed assignments inside expressions (e.g. if (x = 5)).
 */
@Rule(
        key = "qa-convention-no-assignment-in-operand",
        name = "Do not embed assignments inside expressions",
        description = "Assignments inside conditionals are easily confused with equality checks, leading to subtle bugs and harder code reviews",
        tags = {"convention", "confusing"},
        priority = Priority.MINOR
)
public class NoAssignmentInOperandCheck extends BaseCheck {

    private static final Pattern KEYWORD = Pattern.compile("\\b(if|while)\\s*\\(");
    private static final Pattern LONE_ASSIGN = Pattern.compile("(?<![=!<>])=(?!=)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!KEYWORD.matcher(line).find()) continue;
            int openParen = line.indexOf('(');
            if (openParen < 0) continue;
            int closeParen = line.lastIndexOf(')');
            if (closeParen <= openParen) continue;
            String inside = line.substring(openParen + 1, closeParen).trim();
            if (LONE_ASSIGN.matcher(inside).find()) {
                addIssue(i + 1, "Do not use assignment inside conditional or loop expression.");
            }
        }
    }
}
