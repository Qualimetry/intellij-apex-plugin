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
 * Boolean assertions should use isTrue/isFalse instead of assertEquals.
 */
@Rule(
        key = "qa-testing-use-boolean-assert",
        name = "Boolean assertions should use isTrue/isFalse",
        description = "Assert.areEqual(true, x) is verbose; isTrue/isFalse methods are clearer and produce better failure messages",
        tags = {"salesforce", "testing"},
        priority = Priority.MAJOR
)
public class UseBooleanAssertCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Assert.areEqual(")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("Assert.areEqual(true") || line.contains("Assert.areEqual(false") || line.contains("Assert.areEqual( true") || line.contains("Assert.areEqual( false")) {
                addIssue(i + 1, "Use Assert.isTrue or Assert.isFalse for boolean conditions.");
            }
        }
    }
}
