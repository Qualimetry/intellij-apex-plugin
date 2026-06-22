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
 * Test class names must contain 'Test'.
 */
@Rule(
        key = "qa-testing-class-name-includes-test",
        name = "Test class names must contain 'Test'",
        description = "Test classes without 'Test' in their name are hard to discover and identify in the codebase",
        tags = {"testing"},
        priority = Priority.MAJOR
)
public class ClassNameIncludesTestCheck extends BaseCheck {

    private static final Pattern IS_TEST = Pattern.compile("@IsTest|@isTest|@testMethod");
    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        if (!IS_TEST.matcher(content).find()) return;
        java.util.regex.Matcher m = CLASS_NAME.matcher(content);
        while (m.find()) {
            String name = m.group(1);
            if (!name.contains("Test")) {
                int line = lineOf(content, m.start());
                addIssue(line, "Test class names must contain 'Test'.");
                return;
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
