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
import org.sonar.check.RuleProperty;

/**
 * Methods must not exceed the maximum line count.
 */
@Rule(
        key = "qa-complexity-method-line-limit",
        name = "Methods must not exceed the maximum line count",
        description = "Detects methods that exceed the configured maximum line count, suggesting they should be refactored.",
        tags = {"complexity", "brain-overload"},
        priority = Priority.MAJOR
)
public class MethodLineLimitCheck extends BaseCheck {

    private static final int DEFAULT_MAX_LINES = 80;

    @RuleProperty(
            key = "maxLines",
            description = "Maximum number of lines allowed in a method body",
            defaultValue = "" + DEFAULT_MAX_LINES)
    private int maxLines = DEFAULT_MAX_LINES;

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int depth = 0;
        int methodStart = 0;
        int methodLineCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth > 0) methodLineCount++;
            if (depth == 0 && i > 0) {
                if (methodLineCount > maxLines) {
                    addIssue(methodStart + 1, "Method has " + methodLineCount + " lines (max " + maxLines + ").");
                }
                methodLineCount = 0;
                methodStart = i + 1;
            }
        }
        if (methodLineCount > maxLines) {
            addIssue(methodStart + 1, "Method has " + methodLineCount + " lines (max " + maxLines + ").");
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
