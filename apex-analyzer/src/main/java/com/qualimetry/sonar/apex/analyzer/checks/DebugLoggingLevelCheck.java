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
 * System.debug should specify a LoggingLevel.
 */
@Rule(
        key = "qa-bestpractice-debug-logging-level",
        name = "System.debug should specify a LoggingLevel",
        description = "Detects System.debug() calls that omit the LoggingLevel parameter, preventing effective log filtering.",
        tags = {"best-practice", "performance"},
        priority = Priority.MINOR
)
public class DebugLoggingLevelCheck extends BaseCheck {

    private static final Pattern SYSTEM_DEBUG = Pattern.compile("System\\.debug\\s*\\(");
    private static final Pattern LOGGING_LEVEL = Pattern.compile("LoggingLevel\\.\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (SYSTEM_DEBUG.matcher(line).find() && !LOGGING_LEVEL.matcher(line).find()) {
                addIssue(i + 1, "System.debug should specify a LoggingLevel.");
            }
        }
    }
}
