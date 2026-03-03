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

/**
 * Move object instantiation outside of loop bodies.
 */
@Rule(
        key = "qa-performance-no-instantiation-in-loop",
        name = "Move object instantiation outside of loop bodies",
        description = "Creating objects inside loops generates excessive heap allocation and risks the Apex heap size governor limit",
        tags = {"performance"},
        priority = Priority.MINOR
)
public class NoInstantiationInLoopCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int depth = 0;
        boolean inLoop = false;
        int loopStart = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int openBraces = countChar(line, '{');
            int closeBraces = countChar(line, '}');
            if (line.contains("for (") || line.contains("while (")) {
                if (!inLoop) {
                    inLoop = true;
                    loopStart = depth;
                }
            }
            if (inLoop && line.contains(" new ") && line.contains("(")) {
                if (depth >= loopStart) {
                    addIssue(i + 1, "Move object instantiation outside the loop.");
                }
            }
            depth += openBraces - closeBraces;
            if (depth < loopStart) inLoop = false;
        }
    }

    private static int countChar(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) n++;
        }
        return n;
    }
}
