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
 * Variables must not be assigned to themselves.
 */
@Rule(
        key = "qa-error-handling-self-assignment",
        name = "Variables must not be assigned to themselves",
        description = "Detects assignments where the same variable appears on both sides of the operator, which has no effect.",
        tags = {"bug", "suspicious"},
        priority = Priority.MAJOR
)
public class SelfAssignmentCheck extends BaseCheck {

    private static final Pattern SELF_ASSIGN = Pattern.compile("(\\w+)\\s*=\\s*\\1\\s*;");
    private static final Pattern THIS_SELF_ASSIGN = Pattern.compile("this\\.(\\w+)\\s*=\\s*this\\.\\1\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (SELF_ASSIGN.matcher(line).find() || THIS_SELF_ASSIGN.matcher(line).find()) {
                addIssue(i + 1, "Variable must not be assigned to itself.");
            }
        }
    }
}
