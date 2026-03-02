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
 * Test methods must reside in classes annotated with @IsTest.
 */
@Rule(
        key = "qa-testing-methods-in-test-class",
        name = "Test methods must reside in @IsTest classes",
        description = "Placing test methods in production classes mixes concerns and inflates class size",
        tags = {"testing", "bad-practice"},
        priority = Priority.MAJOR
)
public class MethodsInTestClassCheck extends BaseCheck {

    private static final Pattern TEST_METHOD = Pattern.compile("@(?:IsTest|testMethod)\\s*\\n?\\s*(?:static\\s+)?(?:testMethod\\s+)?\\w+\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("@testMethod") && !content.contains("testMethod")) return;
        if (content.contains("@IsTest") || content.contains("@isTest")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("@testMethod")) {
                addIssue(i + 1, "Test methods must reside in a class annotated with @IsTest.");
                return;
            }
        }
    }
}
