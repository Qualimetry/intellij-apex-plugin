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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * If statements must not be nested beyond the allowed depth.
 */
@Rule(
        key = "qa-complexity-nested-if-depth",
        name = "If statements must not be nested beyond the allowed depth",
        description = "Detects if statements nested beyond the configured depth, which reduces readability and increases cognitive load.",
        tags = {"complexity", "brain-overload"},
        priority = Priority.MAJOR
)
public class NestedIfDepthCheck extends BaseCheck {

    private static final int MAX_DEPTH = 3;
    private static final Pattern IF_KEYWORD = Pattern.compile("\\bif\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        Deque<Integer> ifStack = new ArrayDeque<>();
        int braceDepth = 0;
        int issueLine = -1;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher ifMatcher = IF_KEYWORD.matcher(line);
            int ifPos = ifMatcher.find() ? ifMatcher.start() : -1;
            boolean ifOnLine = ifPos >= 0;

            if (ifOnLine) {
                for (int j = 0; j < ifPos; j++) {
                    char c = line.charAt(j);
                    if (c == '{') {
                        braceDepth++;
                    } else if (c == '}') {
                        braceDepth--;
                        while (!ifStack.isEmpty() && ifStack.peek() > braceDepth) {
                            ifStack.pop();
                        }
                    }
                }
            }

            if (ifOnLine) {
                int nesting = ifStack.size() + 1;
                if (nesting > MAX_DEPTH && issueLine < 0) {
                    issueLine = i + 1;
                }
            }

            int startPos = ifOnLine ? ifPos : 0;
            boolean ifPushed = false;
            for (int j = startPos; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == '{') {
                    braceDepth++;
                    if (ifOnLine && !ifPushed) {
                        ifStack.push(braceDepth);
                        ifPushed = true;
                    }
                } else if (c == '}') {
                    braceDepth--;
                    while (!ifStack.isEmpty() && ifStack.peek() > braceDepth) {
                        ifStack.pop();
                    }
                }
            }
        }

        if (issueLine > 0) {
            addIssue(issueLine, "If statements must not be nested beyond depth " + MAX_DEPTH + ".");
        }
    }
}
