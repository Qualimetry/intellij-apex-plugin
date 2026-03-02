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
 * Every Apex class should have a corresponding test class.
 */
@Rule(
        key = "qa-testing-class-requires-test",
        name = "Every Apex class should have a corresponding test class",
        description = "Classes without test coverage are a deployment risk and reduce confidence in code changes",
        tags = {"salesforce", "testing"},
        priority = Priority.MINOR
)
public class ClassRequiresTestCheck extends BaseCheck {

    private static final Pattern IS_TEST = Pattern.compile("@IsTest|@isTest|@testMethod");
    private static final Pattern CLASS_DECL = Pattern.compile("\\bclass\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        if (IS_TEST.matcher(content).find()) return;
        if (content.contains("Test") && content.contains("class")) return;
        java.util.regex.Matcher m = CLASS_DECL.matcher(content);
        if (m.find()) {
            int line = lineOf(content, m.start());
            addIssue(line, "Ensure this class has a corresponding test class.");
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
