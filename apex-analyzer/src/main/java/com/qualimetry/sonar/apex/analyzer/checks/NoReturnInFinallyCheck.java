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
 * Finally blocks should not contain return statements.
 */
@Rule(
        key = "qa-error-handling-no-return-in-finally",
        name = "Finally blocks should not contain return statements",
        description = "A return statement in a finally block silently swallows any exception from the try or catch block, masking failures",
        tags = {"error-handling", "cwe"},
        priority = Priority.MAJOR
)
public class NoReturnInFinallyCheck extends BaseCheck {

    private static final Pattern FINALLY = Pattern.compile("\\bfinally\\s*\\{");
    private static final Pattern RETURN = Pattern.compile("\\breturn\\s+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inFinally = false;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (FINALLY.matcher(line).find()) {
                inFinally = true;
                braceCount = 1;
            }
            if (inFinally) {
                if (line.contains("{")) braceCount++;
                if (RETURN.matcher(line).find()) {
                    addIssue(i + 1, "Finally blocks should not contain return statements.");
                }
                if (line.contains("}")) {
                    braceCount--;
                    if (braceCount <= 0) inFinally = false;
                }
            }
        }
    }
}
