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
 * Catch blocks must add value beyond rethrowing.
 */
@Rule(
        key = "qa-error-handling-catch-must-add-value",
        name = "Catch blocks must add value beyond rethrowing",
        description = "A catch block that only rethrows without logging, wrapping, or recovering provides no benefit and obscures the error flow",
        tags = {"unused", "suspicious"},
        priority = Priority.MAJOR
)
public class CatchMustAddValueCheck extends BaseCheck {

    private static final Pattern CATCH = Pattern.compile("\\bcatch\\s*\\(");
    private static final Pattern THROW_VAR = Pattern.compile("\\bthrow\\s+\\w+\\s*;");
    private static final Pattern LOG_OR_HANDLE = Pattern.compile("(System\\.debug|addError|log|Logger|throw\\s+new)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inCatch = false;
        int braceCount = 0;
        int catchLine = 0;
        boolean hasOther = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int opens = ApexContentHelper.countChar(line, '{');
            int closes = ApexContentHelper.countChar(line, '}');

            if (inCatch) {
                braceCount += opens - closes;
                String t = line.trim();
                if (THROW_VAR.matcher(line).find() && !t.startsWith("//")) {
                    if (!hasOther) addIssue(i + 1, "Catch blocks must add value beyond rethrowing.");
                }
                if (LOG_OR_HANDLE.matcher(line).find()
                        || (t.length() > 0 && !t.startsWith("//")
                        && !t.startsWith("throw ") && !t.equals("}")
                        && !line.contains("catch"))) {
                    hasOther = true;
                }
                if (braceCount <= 0) {
                    inCatch = false;
                }
            }

            if (!inCatch && CATCH.matcher(line).find()) {
                inCatch = true;
                braceCount = opens;
                catchLine = i + 1;
                hasOther = false;
            }
        }
    }
}
