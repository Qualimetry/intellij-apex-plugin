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
 * Exceptions should not be used for regular control flow.
 */
@Rule(
        key = "qa-error-handling-no-exception-flow-control",
        name = "Exceptions should not be used for regular control flow",
        description = "Using try-catch to control normal program flow is significantly slower than conditional checks and obscures the actual logic",
        tags = {"error-handling", "confusing"},
        priority = Priority.MAJOR
)
public class NoExceptionFlowControlCheck extends BaseCheck {

    private static final Pattern THROW_IN_LOOP = Pattern.compile("\\bthrow\\s+");
    private static final Pattern FOR_WHILE = Pattern.compile("\\b(for|while)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int loopDepth = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (FOR_WHILE.matcher(line).find()) loopDepth++;
            if (loopDepth > 0 && THROW_IN_LOOP.matcher(line).find()) {
                addIssue(i + 1, "Exceptions should not be used for regular control flow.");
            }
            if (line.trim().equals("}")) loopDepth = Math.max(0, loopDepth - 1);
        }
    }
}
