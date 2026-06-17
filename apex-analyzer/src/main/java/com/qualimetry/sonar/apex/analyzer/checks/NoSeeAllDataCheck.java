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
 * Test classes and methods must not use seeAllData=true.
 */
@Rule(
        key = "qa-testing-no-see-all-data",
        name = "Test classes and methods must not use seeAllData=true",
        description = "Detects test classes or methods annotated with @IsTest(SeeAllData=true), which couples tests to org data.",
        tags = {"testing", "salesforce"},
        priority = Priority.MAJOR
)
public class NoSeeAllDataCheck extends BaseCheck {

    private static final Pattern SEE_ALL_DATA = Pattern.compile("(?i)seeAllData\\s*=\\s*true");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (SEE_ALL_DATA.matcher(lines[i]).find()) {
                addIssue(i + 1, "Do not use seeAllData=true in tests.");
            }
        }
    }
}
