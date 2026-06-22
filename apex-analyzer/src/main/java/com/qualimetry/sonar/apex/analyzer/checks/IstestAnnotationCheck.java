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

/**
 * Test classes must use the @IsTest annotation.
 */
@Rule(
        key = "qa-testing-istest-annotation",
        name = "Test classes must use the @IsTest annotation",
        description = "Detects test classes using the legacy testMethod keyword instead of the recommended @IsTest annotation.",
        tags = {"testing", "salesforce"},
        priority = Priority.MAJOR
)
public class IstestAnnotationCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("testMethod") && !content.contains("@IsTest")) return;
        if (content.contains("testMethod") && !content.contains("@IsTest")) {
            String[] lines = prepareLines(content);
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("class ") && lines[i].contains("Test")) {
                    addIssue(i + 1, "Test class must use the @IsTest annotation.");
                    return;
                }
            }
        }
    }
}
