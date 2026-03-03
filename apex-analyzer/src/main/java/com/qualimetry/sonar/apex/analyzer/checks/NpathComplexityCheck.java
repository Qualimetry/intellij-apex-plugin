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
 * Method NPath complexity must not exceed threshold.
 * Simplified: approximate NPath by counting decision points and multiplying.
 */
@Rule(
        key = "qa-complexity-npath",
        name = "Method NPath complexity must not exceed threshold",
        description = "NPath complexity grows exponentially with nested conditionals, making exhaustive path testing impractical",
        tags = {"brain-overload"},
        priority = Priority.MAJOR
)
public class NpathComplexityCheck extends BaseCheck {

    private static final int MAX_NPATH = 200;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int depth = 0;
        int methodStart = 0;
        int branches = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (depth == 1 && trimmed.contains("(") && trimmed.contains(")") && (trimmed.contains("{") || (i + 1 < lines.length && lines[i + 1].trim().startsWith("{")))) {
                methodStart = i + 1;
            }
            if (depth > 1) {
                if (trimmed.startsWith("if ") || trimmed.startsWith("if(")) branches++;
                if (trimmed.startsWith("for ") || trimmed.startsWith("for(")) branches++;
                if (trimmed.startsWith("while ") || trimmed.startsWith("while(")) branches++;
                if (trimmed.startsWith("switch ") || trimmed.startsWith("switch(")) branches++;
                if (trimmed.startsWith("case ") || trimmed.contains(" case ")) branches++;
                if (trimmed.startsWith("catch ") || trimmed.startsWith("catch(")) branches++;
                if (trimmed.contains(" ? ") && trimmed.contains(":")) branches++;
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth == 1 && methodStart > 0) {
                int npath = 1;
                for (int b = 0; b < branches; b++) npath *= 2;
                if (npath > MAX_NPATH) addIssue(methodStart, "NPath complexity is " + npath + " (max " + MAX_NPATH + ").");
                methodStart = 0;
                branches = 0;
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
