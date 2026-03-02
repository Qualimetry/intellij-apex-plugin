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
 * SOQL should use COUNT() instead of .size().
 */
@Rule(
        key = "qa-salesforce-soql-count-preferred",
        name = "SOQL should use COUNT() instead of .size()",
        description = "Detects SOQL queries followed by .size() calls that should use COUNT() for better governor limit efficiency.",
        tags = {"salesforce", "performance"},
        priority = Priority.MAJOR
)
public class SoqlCountPreferredCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("SELECT") && line.contains("]") && line.contains(".size()")) {
                addIssue(i + 1, "Use COUNT() in SOQL instead of .size() on the query result.");
            }
        }
    }
}
