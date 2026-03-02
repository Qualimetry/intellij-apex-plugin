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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

/**
 * SOQL and DML must not execute inside loops.
 */
@Rule(
        key = "qa-salesforce-no-dml-soql-in-loop",
        name = "SOQL and DML must not execute inside loops",
        description = "Detects SOQL queries and DML operations placed inside loop bodies, which risk hitting governor limits.",
        tags = {"salesforce", "governor-limits", "bug"},
        priority = Priority.CRITICAL
)
public class NoDmlSoqlInLoopCheck extends BaseCheck {

    private static final Pattern FOR_START = Pattern.compile("(?i)\\bfor\\s*\\(");
    private static final Pattern WHILE_START = Pattern.compile("(?i)\\bwhile\\s*\\(");
    private static final Pattern DO_START = Pattern.compile("(?i)\\bdo\\s*\\{");
    private static final Pattern DO_WHILE_END = Pattern.compile("\\}\\s*while\\s*\\(");
    private static final Pattern SOQL_INLINE = Pattern.compile("(?i)\\[\\s*SELECT\\b");
    private static final Pattern DB_QUERY = Pattern.compile("(?i)\\bDatabase\\.query\\s*\\(");
    private static final Pattern DML_KEYWORD = Pattern.compile("(?i)\\b(insert|update|delete|upsert)\\b");
    private static final Pattern DB_DML = Pattern.compile("(?i)\\bDatabase\\.(insert|update|delete|upsert)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int braceDepth = 0;
        Deque<Integer> loopStack = new ArrayDeque<>();
        boolean pendingLoop = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();

            boolean whileFound = WHILE_START.matcher(line).find();
            boolean isDoWhileEnd = whileFound
                    && (DO_WHILE_END.matcher(line).find() || trimmed.endsWith(";"));
            boolean isLoopLine = FOR_START.matcher(line).find()
                    || (whileFound && !isDoWhileEnd)
                    || DO_START.matcher(line).find();

            boolean inLoop = !loopStack.isEmpty();
            if (inLoop) {
                if (SOQL_INLINE.matcher(line).find() || DB_QUERY.matcher(line).find()) {
                    addIssue(i + 1, "SOQL must not execute inside loops.");
                }
                if (DML_KEYWORD.matcher(line).find() || DB_DML.matcher(line).find()) {
                    addIssue(i + 1, "DML must not execute inside loops.");
                }
            }

            boolean loopPushed = false;
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == '{') {
                    braceDepth++;
                    if ((isLoopLine || pendingLoop) && !loopPushed) {
                        loopStack.push(braceDepth);
                        loopPushed = true;
                        pendingLoop = false;
                    }
                } else if (c == '}') {
                    braceDepth--;
                    while (!loopStack.isEmpty() && loopStack.peek() > braceDepth) {
                        loopStack.pop();
                    }
                }
            }

            if (isLoopLine && !loopPushed) {
                pendingLoop = true;
            }
        }
    }
}
