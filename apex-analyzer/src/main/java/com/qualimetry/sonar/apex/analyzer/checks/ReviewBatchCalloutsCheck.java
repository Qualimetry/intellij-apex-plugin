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
 * Review use of Database.AllowsCallouts in batch classes.
 */
@Rule(
        key = "qa-salesforce-review-batch-callouts",
        name = "Review Database.AllowsCallouts in batch classes",
        description = "Batch callouts require careful error handling, timeout configuration, and idempotency verification",
        tags = {"salesforce", "design"},
        priority = Priority.MINOR
)
public class ReviewBatchCalloutsCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Database.Batchable") && !content.contains("implements Batchable")) return;
        if (!content.contains("AllowsCallouts")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("AllowsCallouts")) {
                addIssue(i + 1, "Review Database.AllowsCallouts usage; ensure callouts are necessary and governor limits are respected.");
            }
        }
    }
}
