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
 * Method non-commenting source statements must not exceed threshold.
 */
@Rule(
        key = "qa-complexity-ncss-method",
        name = "Method NCSS must not exceed threshold",
        description = "Long methods are hard to understand, test, and debug; extract logical blocks into helper methods",
        tags = {"brain-overload"},
        priority = Priority.MAJOR
)
public class NcssMethodCheck extends BaseCheck {

    private static final int MAX_NCSS = 50;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int depth = 0;
        int methodStart = 0;
        int ncss = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (depth == 1 && trimmed.contains("(") && trimmed.contains(")") && (trimmed.contains("{") || (i + 1 < lines.length && lines[i + 1].trim().startsWith("{")))) {
                methodStart = i + 1;
            }
            if (depth > 1 && methodStart > 0 && !trimmed.isEmpty() && !trimmed.startsWith("//") && !trimmed.startsWith("*")) {
                ncss++;
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth == 1 && methodStart > 0) {
                if (ncss > MAX_NCSS) addIssue(methodStart, "Method NCSS is " + ncss + " (max " + MAX_NCSS + ").");
                methodStart = 0;
                ncss = 0;
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
