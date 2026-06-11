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
 * Loop incrementers must correspond to the correct loop variable.
 */
@Rule(
        key = "qa-error-handling-jumbled-incrementer",
        name = "Loop incrementers must correspond to the correct loop variable",
        description = "Incrementing the wrong variable in a nested loop's update expression can cause infinite loops or skipped iterations",
        tags = {"misra", "confusing"},
        priority = Priority.MAJOR
)
public class JumbledIncrementerCheck extends BaseCheck {

    private static final Pattern FOR_LOOP = Pattern.compile("for\\s*\\([^;]*;\\s*[^;]*;\\s*([^)]+)\\)");
    private static final Pattern FOR_INIT = Pattern.compile("for\\s*\\(\\s*(?:\\w+\\s+)?(\\w+)\\s*=");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            var initMatcher = FOR_INIT.matcher(line);
            var updateMatcher = FOR_LOOP.matcher(line);
            if (initMatcher.find() && updateMatcher.find()) {
                String loopVar = initMatcher.group(1);
                String update = updateMatcher.group(1).trim();
                if (!update.contains(loopVar) && (update.contains("++") || update.contains("--") || update.contains("+=") || update.contains("-="))) {
                    addIssue(i + 1, "Loop incrementers must correspond to the correct loop variable.");
                }
            }
        }
    }
}
