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
 * Methods must limit the number of return points.
 */
@Rule(
        key = "qa-complexity-max-return-statements",
        name = "Methods must limit the number of return points",
        description = "Too many return points make it hard to track exit conditions and indicate the method is doing too much",
        tags = {"brain-overload"},
        priority = Priority.MAJOR
)
public class MaxReturnStatementsCheck extends BaseCheck {

    private static final int MAX_RETURNS = 3;

    @Override
    public void scan(InputFile file, String content) {
        content = content.replace("\r\n", "\n").replace("\r", "");
        String[] lines = prepareLines(content);
        int depth = 0;
        int methodStart = 0;
        int returnCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (depth >= 1 && (trimmed.contains(" return ") || trimmed.startsWith("return ") || trimmed.equals("return;"))) returnCount++;
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth == 0 && i > 0) {
                if (returnCount > MAX_RETURNS) {
                    addIssue(methodStart + 1, "Method has " + returnCount + " return statements (max " + MAX_RETURNS + ").");
                }
                returnCount = 0;
                methodStart = i + 1;
            }
        }
        if (returnCount > MAX_RETURNS) {
            addIssue(methodStart + 1, "Method has " + returnCount + " return statements (max " + MAX_RETURNS + ").");
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
