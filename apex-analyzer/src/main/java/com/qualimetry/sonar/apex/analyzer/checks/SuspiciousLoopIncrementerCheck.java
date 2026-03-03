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
 * For-loop update expressions should modify the loop variable.
 */
@Rule(
        key = "qa-error-handling-suspicious-loop-incrementer",
        name = "For-loop update expressions should modify the loop variable",
        description = "A for-loop update that modifies a variable other than the declared loop variable likely causes an infinite loop or unintended behavior",
        tags = {"pitfall"},
        priority = Priority.MAJOR
)
public class SuspiciousLoopIncrementerCheck extends BaseCheck {

    private static final Pattern FOR_INIT = Pattern.compile("for\\s*\\(\\s*(?:\\w+\\s+)?(\\w+)\\s*=");
    private static final Pattern FOR_UPDATE = Pattern.compile("for\\s*\\([^;]*;[^;]*;\\s*([^)]+)\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            var initMatcher = FOR_INIT.matcher(line);
            var updateMatcher = FOR_UPDATE.matcher(line);
            if (initMatcher.find() && updateMatcher.find()) {
                String loopVar = initMatcher.group(1);
                String update = updateMatcher.group(1).trim();
                if (!update.contains(loopVar)) {
                    addIssue(i + 1, "For-loop update expressions should modify the loop variable.");
                }
            }
        }
    }
}
