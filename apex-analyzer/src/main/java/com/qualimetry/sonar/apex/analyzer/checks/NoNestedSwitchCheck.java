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
 * Switch statements must not be nested.
 */
@Rule(
        key = "qa-design-no-nested-switch",
        name = "Switch statements must not be nested",
        description = "Detects switch statements nested inside other switch statements, which are hard to read and maintain.",
        tags = {"design", "confusing"},
        priority = Priority.MAJOR
)
public class NoNestedSwitchCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int depth = 0;
        int switchDepth = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (trimmed.startsWith("switch ") || trimmed.startsWith("switch(")) {
                switchDepth++;
                if (switchDepth > 1) {
                    addIssue(i + 1, "Do not nest switch statements.");
                }
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth <= 0) switchDepth = 0;
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
