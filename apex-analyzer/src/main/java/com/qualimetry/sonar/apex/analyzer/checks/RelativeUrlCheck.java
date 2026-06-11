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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

/**
 * Salesforce page URLs must use relative paths.
 */
@Rule(
        key = "qa-salesforce-relative-url",
        name = "Salesforce page URLs must use relative paths",
        description = "Detects hardcoded absolute URLs to Salesforce pages that should use relative paths for portability.",
        tags = {"salesforce", "convention"},
        priority = Priority.MAJOR
)
public class RelativeUrlCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("https://") || line.contains("http://")) {
                if (line.contains("salesforce.com") || line.contains("force.com")) {
                    addIssue(i + 1, "Use relative page URLs instead of absolute Salesforce URLs.");
                }
            }
        }
    }
}
