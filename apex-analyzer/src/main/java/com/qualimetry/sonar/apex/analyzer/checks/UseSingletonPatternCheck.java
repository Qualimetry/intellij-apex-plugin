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
 * Classes with only static methods should use the singleton pattern.
 */
@Rule(
        key = "qa-design-use-singleton-pattern",
        name = "Classes with only static methods should use the singleton pattern",
        description = "A class containing only static methods and fields should adopt the singleton pattern to control instantiation and improve testability",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class UseSingletonPatternCheck extends BaseCheck {

    private static final Pattern STATIC_METHOD = Pattern.compile("\\bstatic\\s+\\w+\\s+\\w+\\s*\\(");
    private static final Pattern INSTANCE_FIELD = Pattern.compile("\\b(?:private|public)\\s+static\\s+\\w+\\s+instance\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ");
        if (STATIC_METHOD.matcher(normalized).find() && !INSTANCE_FIELD.matcher(normalized).find()
                && !normalized.contains("getInstance(")) {
            String[] lines = prepareLines(content);
            for (int i = 0; i < lines.length; i++) {
                if (Pattern.compile("\\bclass\\s+\\w+").matcher(lines[i]).find()) {
                    addIssue(i + 1, "Classes with only static methods should use the singleton pattern.");
                    return;
                }
            }
        }
    }
}
