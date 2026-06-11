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
 * Tests verifying permissions should use System.runAs.
 */
@Rule(
        key = "qa-testing-use-runas",
        name = "Tests verifying permissions should use System.runAs",
        description = "Detects permission-related tests that do not use System.runAs() to verify behavior under different user profiles.",
        tags = {"testing", "salesforce"},
        priority = Priority.MAJOR
)
public class UseRunasCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("@IsTest") && !content.contains("testMethod")) return;
        boolean hasRunAs = content.contains("System.runAs");
        boolean hasPermissionCheck = content.contains("getDescribe") && (content.contains("isAccessible") || content.contains("isCreateable") || content.contains("isUpdateable"));
        if (hasPermissionCheck && !hasRunAs) {
            String[] lines = prepareLines(content);
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("isAccessible") || lines[i].contains("isCreateable") || lines[i].contains("isUpdateable")) {
                    addIssue(i + 1, "Tests verifying permissions should use System.runAs.");
                    return;
                }
            }
        }
    }
}
