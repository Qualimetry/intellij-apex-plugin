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
 * Type non-commenting source statements must not exceed threshold.
 */
@Rule(
        key = "qa-complexity-ncss-type",
        name = "Type NCSS must not exceed threshold",
        description = "Large classes with too much code indicate multiple responsibilities that should be split into focused components",
        tags = {"complexity", "brain-overload"},
        priority = Priority.MAJOR
)
public class NcssTypeCheck extends BaseCheck {

    private static final int MAX_NCSS = 500;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int depth = 0;
        int typeStart = 0;
        int ncss = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (depth == 0 && (trimmed.startsWith("class ") || trimmed.contains(" class "))) {
                typeStart = i + 1;
            }
            if (depth > 0 && typeStart > 0 && !trimmed.isEmpty() && !trimmed.startsWith("//") && !trimmed.startsWith("*")) {
                ncss++;
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth == 0 && typeStart > 0) {
                if (ncss > MAX_NCSS) addIssue(typeStart, "Type NCSS is " + ncss + " (max " + MAX_NCSS + ").");
                typeStart = 0;
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
