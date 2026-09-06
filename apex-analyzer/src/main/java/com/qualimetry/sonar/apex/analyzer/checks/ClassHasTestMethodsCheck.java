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
 * Test classes must contain at least one test method.
 */
@Rule(
        key = "qa-testing-class-has-test-methods",
        name = "Test classes must contain at least one test method",
        description = "A @IsTest class with no test methods provides no coverage and misleads developers about test status",
        tags = {"testing", "bad-practice"},
        priority = Priority.MAJOR
)
public class ClassHasTestMethodsCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("@IsTest")) return;
        String[] lines = prepareLines(content);
        boolean hasTestMethod = false;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("@testMethod")) hasTestMethod = true;
            if (lines[i].contains("@IsTest") && (lines[i].contains("void") || (i + 1 < lines.length && lines[i + 1].trim().startsWith("void"))))
                hasTestMethod = true;
        }
        if (hasTestMethod) return;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("class ") && content.contains("@IsTest")) {
                addIssue(i + 1, "Test class must contain at least one test method.");
                return;
            }
        }
    }
}
