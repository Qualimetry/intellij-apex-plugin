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

import java.util.regex.Pattern;

/**
 * Finally blocks must not throw exceptions directly.
 */
@Rule(
        key = "qa-error-handling-no-throw-from-finally",
        name = "Finally blocks must not throw exceptions directly",
        description = "Throwing directly from a finally block replaces any in-flight exception from the try block, losing the original failure context",
        tags = {"error-handling", "cwe"},
        priority = Priority.CRITICAL
)
public class NoThrowFromFinallyCheck extends BaseCheck {

    private static final Pattern FINALLY = Pattern.compile("\\bfinally\\s*\\{");
    private static final Pattern THROW = Pattern.compile("\\bthrow\\s+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inFinally = false;
        int braceCount = 0;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int opens = ApexContentHelper.countChar(line, '{');
            int closes = ApexContentHelper.countChar(line, '}');

            if (inFinally) {
                braceCount += opens - closes;
                if (THROW.matcher(line).find()) {
                    addIssue(i + 1, "Finally blocks must not throw exceptions directly.");
                }
                if (braceCount <= 0) {
                    inFinally = false;
                }
            }

            if (!inFinally && FINALLY.matcher(line).find()) {
                inFinally = true;
                braceCount = opens;
            }
        }
    }
}
