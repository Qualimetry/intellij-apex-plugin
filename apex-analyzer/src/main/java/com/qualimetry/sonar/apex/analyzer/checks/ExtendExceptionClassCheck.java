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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom exception classes must extend System.Exception.
 */
@Rule(
        key = "qa-error-handling-extend-exception-class",
        name = "Custom exception classes must extend System.Exception",
        description = "Apex requires all custom exception classes to extend Exception; classes with 'Exception' in the name that do not extend it cannot be thrown or caught",
        tags = {"convention"},
        priority = Priority.CRITICAL
)
public class ExtendExceptionClassCheck extends BaseCheck {

    private static final Pattern CLASS_EXCEPTION_EXTENDS = Pattern.compile("class\\s+\\w+Exception\\s+extends\\s+(\\S+)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher m = CLASS_EXCEPTION_EXTENDS.matcher(line);
            if (m.find()) {
                String superClass = m.group(1).replaceFirst("[{].*", "").trim();
                if (!"Exception".equals(superClass) && !"System.Exception".equals(superClass)) {
                    addIssue(i + 1, "Custom exception classes must extend System.Exception.");
                }
            }
        }
    }
}
