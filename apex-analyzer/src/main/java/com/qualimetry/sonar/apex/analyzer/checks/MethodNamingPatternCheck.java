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
 * Method names must conform to naming pattern (camelCase).
 */
@Rule(
        key = "qa-convention-method-naming-pattern",
        name = "Method names must conform to naming pattern",
        description = "Detects method names that do not match the configured naming pattern, defaulting to camelCase convention.",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class MethodNamingPatternCheck extends BaseCheck {

    private static final Pattern METHOD_NAME_PATTERN = Pattern.compile(
            "\\b(?:public|private|global|protected|@\\w+)\\s+(?:static\\s+)?(?:override\\s+)?(?:final\\s+)?(?:\\w+\\s+)?(\\w+)\\s*\\s*\\("
    );
    private static final Pattern CAMEL_CASE = Pattern.compile("^[a-z][a-zA-Z0-9]*$");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = normalized.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Matcher m = METHOD_NAME_PATTERN.matcher(lines[i]);
            while (m.find()) {
                String name = m.group(1);
                if ("get".equals(name) || "set".equals(name)) continue;
                if (!CAMEL_CASE.matcher(name).matches()) {
                    addIssue(i + 1, "Method name '" + name + "' should follow camelCase convention.");
                }
            }
        }
    }
}
