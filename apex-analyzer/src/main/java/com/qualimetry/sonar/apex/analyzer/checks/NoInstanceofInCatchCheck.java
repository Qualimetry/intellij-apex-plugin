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
 * Catch blocks should not use instanceof to differentiate exceptions.
 */
@Rule(
        key = "qa-error-handling-no-instanceof-in-catch",
        name = "Catch blocks should not use instanceof to differentiate exceptions",
        description = "Using instanceof inside a catch block to differentiate exceptions bypasses the type system; use separate catch clauses instead",
        tags = {"error-handling", "cert"},
        priority = Priority.MAJOR
)
public class NoInstanceofInCatchCheck extends BaseCheck {

    private static final Pattern CATCH = Pattern.compile("\\bcatch\\s*\\(");
    private static final Pattern INSTANCEOF = Pattern.compile("\\binstanceof\\b");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inCatchBlock = false;
        int catchBraceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (CATCH.matcher(line).find()) {
                inCatchBlock = true;
                catchBraceCount = 1;
            }
            if (inCatchBlock) {
                if (line.contains("{")) catchBraceCount++;
                if (INSTANCEOF.matcher(line).find()) {
                    addIssue(i + 1, "Catch blocks should not use instanceof to differentiate exceptions.");
                }
                if (line.contains("}")) {
                    catchBraceCount--;
                    if (catchBraceCount <= 0) inCatchBlock = false;
                }
            }
        }
    }
}
