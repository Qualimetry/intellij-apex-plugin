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
 * Loops without init/update expressions should use while.
 */
@Rule(
        key = "qa-design-for-loop-should-be-while",
        name = "Loops without init/update expressions should use while",
        description = "A for loop that omits its initialization or update expression is effectively a while loop and should use while for clarity",
        tags = {"clumsy"},
        priority = Priority.MAJOR
)
public class ForLoopShouldBeWhileCheck extends BaseCheck {

    private static final Pattern FOR_EMPTY_INIT = Pattern.compile("\\bfor\\s*\\(\\s*;[^;]+;[^)]*\\)");
    private static final Pattern FOR_EMPTY_UPDATE = Pattern.compile("\\bfor\\s*\\([^;]+;[^;]+;\\s*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (FOR_EMPTY_INIT.matcher(lines[i]).find() || FOR_EMPTY_UPDATE.matcher(lines[i]).find()) {
                addIssue(i + 1, "Loops without init/update expressions should use while.");
            }
        }
    }
}
