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
 * Test methods should bracket code with Test.startTest/stopTest.
 */
@Rule(
        key = "qa-testing-start-stop-test",
        name = "Test methods should bracket code with Test.startTest/stopTest",
        description = "Without Test.startTest/stopTest, setup DML counts against tested code limits and async operations do not complete",
        tags = {"testing", "salesforce"},
        priority = Priority.MAJOR
)
public class StartStopTestCheck extends BaseCheck {

    private static final Pattern TEST_ANNOTATION = Pattern.compile("(?i)@IsTest\\b");
    private static final Pattern TEST_METHOD = Pattern.compile("(?i)\\btestMethod\\b");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        String[] lines = stripped.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (TEST_ANNOTATION.matcher(lines[i]).find() || TEST_METHOD.matcher(lines[i]).find()) {
                int methodStart = findNextOpenBrace(lines, i);
                if (methodStart < 0) continue;
                String body = extractBody(lines, methodStart);
                if (body != null) {
                    boolean hasStart = body.contains("Test.startTest");
                    boolean hasStop = body.contains("Test.stopTest");
                    if (!hasStart || !hasStop) {
                        addIssue(i + 1, "Test method should use Test.startTest() and Test.stopTest().");
                    }
                }
            }
        }
    }

    private static int findNextOpenBrace(String[] lines, int from) {
        for (int i = from; i < lines.length; i++) {
            if (lines[i].contains("{")) return i;
        }
        return -1;
    }

    private static String extractBody(String[] lines, int braceLineIndex) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        for (int i = braceLineIndex; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == '{') depth++;
                else if (c == '}') {
                    depth--;
                    if (depth == 0) return sb.toString();
                }
                if (depth > 0) sb.append(c);
            }
            sb.append('\n');
        }
        return null;
    }
}
