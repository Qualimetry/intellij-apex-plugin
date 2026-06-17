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
 * Batch Apex should not be invoked from triggers.
 */
@Rule(
        key = "qa-salesforce-no-batch-from-trigger",
        name = "Batch Apex should not be invoked from triggers",
        description = "Triggers can fire multiple times per transaction, quickly exhausting the 5-job concurrent batch limit",
        tags = {"salesforce", "design"},
        priority = Priority.MAJOR
)
public class NoBatchFromTriggerCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("trigger ") || !content.contains(" on ")) return;
        if (!content.contains("Database.executeBatch") && !content.contains("executeBatch")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("executeBatch") || lines[i].contains("Database.executeBatch")) {
                addIssue(i + 1, "Do not invoke Batch Apex from a trigger; use Queueable or future methods.");
            }
        }
    }
}
