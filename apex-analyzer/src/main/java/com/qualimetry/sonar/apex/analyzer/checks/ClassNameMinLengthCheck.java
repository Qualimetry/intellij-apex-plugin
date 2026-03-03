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
 * Class names must meet minimum length requirement (default 3).
 */
@Rule(
        key = "qa-convention-class-name-min-length",
        name = "Class names must meet minimum length requirement",
        description = "Very short class names reduce readability and make it harder to understand the class purpose at a glance",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class ClassNameMinLengthCheck extends BaseCheck {

    private static final int MIN_LENGTH = 3;
    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        Matcher m = CLASS_NAME.matcher(content);
        while (m.find()) {
            if (m.group(1).length() < MIN_LENGTH) {
                addIssue(lineOf(content, m.start()), "Class name must be at least " + MIN_LENGTH + " characters.");
            }
        }
    }

    private static int lineOf(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
