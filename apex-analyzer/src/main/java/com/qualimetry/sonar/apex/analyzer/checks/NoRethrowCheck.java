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
 * Catch blocks should not merely rethrow the same exception type.
 */
@Rule(
        key = "qa-error-handling-no-rethrow",
        name = "Catch blocks should not merely rethrow the same exception type",
        description = "A catch block that only rethrows the caught exception adds no value and should be removed or enhanced with logging or wrapping",
        tags = {"error-handling", "cert"},
        priority = Priority.MAJOR
)
public class NoRethrowCheck extends BaseCheck {

    private static final Pattern CATCH = Pattern.compile("\\bcatch\\s*\\([^)]*\\s+(\\w+)\\s*\\)");
    private static final Pattern THROW_IDENT = Pattern.compile("\\bthrow\\s+(\\w+)\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        String catchVar = null;
        boolean inCatch = false;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            var catchMatcher = CATCH.matcher(line);
            if (catchMatcher.find()) {
                catchVar = catchMatcher.group(1);
                inCatch = true;
                braceCount = 1;
            }
            if (inCatch && catchVar != null) {
                if (line.contains("{")) braceCount++;
                var throwMatcher = THROW_IDENT.matcher(line);
                if (throwMatcher.find() && throwMatcher.group(1).equals(catchVar)) {
                    addIssue(i + 1, "Catch blocks should not merely rethrow the same exception type.");
                }
                if (line.contains("}")) {
                    braceCount--;
                    if (braceCount <= 0) {
                        inCatch = false;
                        catchVar = null;
                    }
                }
            }
        }
    }
}
