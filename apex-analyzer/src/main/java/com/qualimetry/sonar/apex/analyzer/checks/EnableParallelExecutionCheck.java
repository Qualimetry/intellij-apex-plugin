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
 * Test classes should enable parallel execution with @IsTest(isParallel=true).
 */
@Rule(
        key = "qa-testing-enable-parallel-execution",
        name = "Test classes should enable @IsTest(isParallel=true)",
        description = "Sequential test execution increases CI pipeline duration; enable parallel execution to reduce feedback cycles",
        tags = {"testing"},
        priority = Priority.MINOR
)
public class EnableParallelExecutionCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("@IsTest")) return;
        if (content.contains("isParallel=true") || content.contains("IsParallel=true")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("@IsTest") && !lines[i].contains("isParallel")) {
                addIssue(i + 1, "Consider @IsTest(isParallel=true) for faster test execution.");
                return;
            }
        }
    }
}
