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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assertion calls should include a descriptive failure message.
 */
@Rule(
        key = "qa-testing-assert-message-required",
        name = "Assertion calls should include a descriptive message",
        description = "Assertions without messages produce uninformative failures that are difficult to diagnose",
        tags = {"testing", "bad-practice"},
        priority = Priority.MINOR
)
public class AssertMessageRequiredCheck extends BaseCheck {

    private static final Pattern THREE_ARG = Pattern.compile(
            "(?i)Assert\\.(?:areEqual|areNotEqual)\\s*\\(");
    private static final Pattern TWO_ARG = Pattern.compile(
            "(?i)Assert\\.(?:isTrue|isFalse|isNull|isNotNull|isInstanceOfType)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        String[] lines = ApexContentHelper.splitLines(stripped);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher m3 = THREE_ARG.matcher(line);
            while (m3.find()) {
                String args = extractArgs(stripped, offsetOfLine(stripped, i) + m3.end() - 1);
                if (args != null && countTopLevelCommas(args) < 2) {
                    addIssue(i + 1, "Include a descriptive message as the last argument to assertion methods.");
                }
            }
            Matcher m2 = TWO_ARG.matcher(line);
            while (m2.find()) {
                String args = extractArgs(stripped, offsetOfLine(stripped, i) + m2.end() - 1);
                if (args != null && countTopLevelCommas(args) < 1) {
                    addIssue(i + 1, "Include a descriptive message as the last argument to assertion methods.");
                }
            }
        }
    }

    private static int offsetOfLine(String content, int lineIndex) {
        int offset = 0;
        for (int i = 0; i < lineIndex; i++) {
            int nl = content.indexOf('\n', offset);
            if (nl < 0) break;
            offset = nl + 1;
        }
        return offset;
    }

    private static String extractArgs(String content, int openParen) {
        if (openParen >= content.length() || content.charAt(openParen) != '(') return null;
        int depth = 0;
        int start = openParen + 1;
        for (int i = openParen; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '(') depth++;
            else if (c == ')') {
                depth--;
                if (depth == 0) return content.substring(start, i);
            }
        }
        return null;
    }

    private static int countTopLevelCommas(String args) {
        int count = 0;
        int depth = 0;
        for (int i = 0; i < args.length(); i++) {
            char c = args.charAt(i);
            if (c == '(' || c == '[') depth++;
            else if (c == ')' || c == ']') depth--;
            else if (c == ',' && depth == 0) count++;
        }
        return count;
    }
}
