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
 * Custom exception class names must end with Exception.
 */
@Rule(
        key = "qa-convention-exception-naming",
        name = "Custom exception names must end with Exception",
        description = "Detects custom exception classes whose names do not end with 'Exception', violating Apex naming conventions.",
        tags = {"convention", "naming"},
        priority = Priority.MAJOR
)
public class ExceptionNamingCheck extends BaseCheck {

    private static final Pattern EXTENDS_EXCEPTION = Pattern.compile("class\\s+([A-Za-z0-9_]+)\\s+extends\\s+Exception");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        for (int i = 0; i < normLines.length; i++) {
            java.util.regex.Matcher m = EXTENDS_EXCEPTION.matcher(normLines[i]);
            if (m.find()) {
                String name = m.group(1);
                if (!name.endsWith("Exception")) {
                    addIssue(i + 1, "Custom exception class name must end with Exception.");
                }
            }
        }
    }
}
