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
 * Class names must conform to the naming pattern.
 */
@Rule(
        key = "qa-convention-class-naming-pattern",
        name = "Class names must conform to the naming pattern",
        description = "Detects class names that do not match the configured naming pattern, defaulting to PascalCase convention.",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class ClassNamingPatternCheck extends BaseCheck {

    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+([A-Za-z0-9_]+)");
    private static final Pattern PASCAL_CASE = Pattern.compile("^[A-Z][a-zA-Z0-9]*$");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        for (int i = 0; i < normLines.length; i++) {
            java.util.regex.Matcher m = CLASS_NAME.matcher(normLines[i]);
            if (m.find()) {
                String name = m.group(1);
                if (!PASCAL_CASE.matcher(name).matches()) {
                    addIssue(i + 1, "Class names should use PascalCase.");
                }
            }
        }
    }
}
