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
 * Do not wrap an exception in a new instance of the same type.
 */
@Rule(
        key = "qa-error-handling-no-same-exception-wrap",
        name = "Do not wrap an exception in a new instance of the same type",
        description = "Wrapping an exception in a new instance of the same exception type adds a redundant layer without additional context",
        tags = {"error-handling", "cert"},
        priority = Priority.MINOR
)
public class NoSameExceptionWrapCheck extends BaseCheck {

    private static final Pattern CATCH_TYPE = Pattern.compile("\\bcatch\\s*\\(\\s*([A-Z]\\w*Exception)\\s+");
    private static final Pattern THROW_NEW_SAME = Pattern.compile("throw\\s+new\\s+([A-Z]\\w*Exception)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        String catchType = null;
        boolean inCatch = false;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            var catchMatcher = CATCH_TYPE.matcher(line);
            if (catchMatcher.find()) {
                catchType = catchMatcher.group(1);
                inCatch = true;
                braceCount = 1;
            }
            if (inCatch && catchType != null) {
                if (line.contains("{")) braceCount++;
                var throwMatcher = THROW_NEW_SAME.matcher(line);
                if (throwMatcher.find() && throwMatcher.group(1).equals(catchType)) {
                    addIssue(i + 1, "Do not wrap an exception in a new instance of the same type.");
                }
                if (line.contains("}")) {
                    braceCount--;
                    if (braceCount <= 0) {
                        inCatch = false;
                        catchType = null;
                    }
                }
            }
        }
    }
}
