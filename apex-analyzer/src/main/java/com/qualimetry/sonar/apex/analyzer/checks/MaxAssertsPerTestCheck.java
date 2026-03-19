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
 * Test methods should not have excessive assertion count.
 */
@Rule(
        key = "qa-testing-max-asserts-per-test",
        name = "Test methods should not have excessive assertion count",
        description = "Too many assertions in one test method hide subsequent failures and indicate the test covers multiple behaviors",
        tags = {"testing"},
        priority = Priority.MINOR
)
public class MaxAssertsPerTestCheck extends BaseCheck {

    private static final int MAX_ASSERTS = 10;

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("@IsTest") && !content.contains("Assert.")) return;
        String[] lines = prepareLines(content);
        int assertCount = 0;
        int methodStart = 0;
        int depth = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            depth += countChar(line, '{') - countChar(line, '}');
            if (line.contains("Assert.") || line.contains("System.assert")) assertCount++;
            if (depth == 0 && i > 0) {
                if (assertCount > MAX_ASSERTS) {
                    addIssue(methodStart + 1, "Consider splitting test; too many assertions (max " + MAX_ASSERTS + ").");
                }
                assertCount = 0;
                methodStart = i + 1;
            }
        }
        if (assertCount > MAX_ASSERTS) {
            addIssue(methodStart + 1, "Consider splitting test; too many assertions (max " + MAX_ASSERTS + ").");
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
