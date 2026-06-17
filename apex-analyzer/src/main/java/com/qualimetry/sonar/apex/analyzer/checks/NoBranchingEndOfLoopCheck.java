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
 * Loop bodies should not end with a branching statement.
 */
@Rule(
        key = "qa-error-handling-no-branching-end-of-loop",
        name = "Loop bodies should not end with a branching statement",
        description = "A loop whose last statement is break or return always exits after one iteration, indicating a logic error or unnecessary loop",
        tags = {"suspicious", "clumsy"},
        priority = Priority.MAJOR
)
public class NoBranchingEndOfLoopCheck extends BaseCheck {

    private static final Pattern LOOP_START = Pattern.compile("\\b(for|while)\\s*\\(");
    private static final Pattern BRANCHING = Pattern.compile("\\b(return|break|continue)\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int loopDepth = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (LOOP_START.matcher(line).find()) loopDepth++;
            if (loopDepth > 0 && BRANCHING.matcher(line).find()) {
                int j = i + 1;
                while (j < lines.length && lines[j].trim().isEmpty()) j++;
                if (j < lines.length && lines[j].trim().equals("}")) {
                    addIssue(i + 1, "Loop bodies should not end with a branching statement.");
                }
            }
            if (line.trim().equals("}")) loopDepth = Math.max(0, loopDepth - 1);
        }
    }
}
