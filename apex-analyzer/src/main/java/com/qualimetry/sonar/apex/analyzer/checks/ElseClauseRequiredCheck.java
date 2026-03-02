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
 * if/else-if chains must end with an else clause.
 */
@Rule(
        key = "qa-design-else-clause-required",
        name = "if/else-if chains must end with an else clause",
        description = "Detects if/else-if chains that do not end with a final else clause to handle unexpected cases.",
        tags = {"design", "cert"},
        priority = Priority.MAJOR
)
public class ElseClauseRequiredCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = normalized.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;
            if (line.matches(".*}\\s*else\\s+if\\s*\\(.*")) {
                int depth = 0;
                boolean foundElse = false;
                for (int j = i + 1; j < lines.length; j++) {
                    String l = lines[j];
                    depth += countChar(l, '{') - countChar(l, '}');
                    if (depth < 0) break;
                    if (depth == 0) {
                        String t = l.trim();
                        if (t.startsWith("} else {") || (t.startsWith("} else") && !t.startsWith("} else if"))) {
                            foundElse = true;
                            break;
                        }
                        if (t.startsWith("}") || t.startsWith("else ")) break;
                    }
                }
                if (!foundElse) {
                    addIssue(i + 1, "if/else-if chain should end with an else clause.");
                }
            }
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
