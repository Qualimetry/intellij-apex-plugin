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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Two-pass check: first collects {@code @Future} method names, then flags
 * calls to those methods (or {@code System.enqueueJob}) inside loops.
 */
@Rule(
        key = "qa-salesforce-no-future-in-loop",
        name = "@Future methods must not be invoked inside loops",
        description = "Detects @Future method calls inside loops, which can quickly exhaust asynchronous Apex governor limits.",
        tags = {"salesforce", "governor-limits", "bug"},
        priority = Priority.CRITICAL
)
public class NoFutureInLoopCheck extends BaseCheck {

    private static final Pattern FUTURE_ANN = Pattern.compile("@(?i)future\\b");
    private static final Pattern METHOD_DECL = Pattern.compile("\\w+\\s+(\\w+)\\s*\\(");
    private static final Pattern LOOP_START = Pattern.compile("(?i)\\b(?:for|while)\\s*\\(|\\bdo\\s*\\{");
    private static final Pattern ENQUEUE_JOB = Pattern.compile("System\\.enqueueJob\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);

        List<String> futureMethods = collectFutureMethods(lines);

        int loopDepth = 0;
        int braceDepth = 0;
        int[] loopBraceStack = new int[lines.length];
        int stackTop = -1;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (LOOP_START.matcher(line).find()) {
                stackTop++;
                loopBraceStack[stackTop] = braceDepth;
                loopDepth++;
            }

            if (loopDepth > 0) {
                if (isAsyncCall(line, futureMethods)) {
                    addIssue(i + 1, "Do not invoke @Future methods inside loops.");
                }
            }

            braceDepth += ApexContentHelper.countChar(line, '{')
                        - ApexContentHelper.countChar(line, '}');

            while (stackTop >= 0 && braceDepth <= loopBraceStack[stackTop]) {
                stackTop--;
                loopDepth--;
            }
        }
    }

    private static List<String> collectFutureMethods(String[] lines) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (FUTURE_ANN.matcher(lines[i]).find()) {
                for (int j = i; j < lines.length && j <= i + 3; j++) {
                    Matcher m = METHOD_DECL.matcher(lines[j]);
                    if (m.find()) {
                        names.add(m.group(1));
                        break;
                    }
                }
            }
        }
        return names;
    }

    private static boolean isAsyncCall(String line, List<String> futureMethods) {
        for (String name : futureMethods) {
            if (line.matches(".*\\b" + Pattern.quote(name) + "\\s*\\(.*")) {
                return true;
            }
        }
        return ENQUEUE_JOB.matcher(line).find();
    }
}
