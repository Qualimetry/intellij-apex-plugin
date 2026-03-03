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
 * Access Trigger context records through iteration, not by index.
 */
@Rule(
        key = "qa-salesforce-iterate-trigger-context",
        name = "Access Trigger context through iteration, not by index",
        description = "Index-based access to Trigger.new fails during bulk operations that process up to 200 records",
        tags = {"salesforce"},
        priority = Priority.MAJOR
)
public class IterateTriggerContextCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Trigger.new") && !content.contains("Trigger.old")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if ((line.contains("Trigger.new[") || line.contains("Trigger.old[")) && line.contains("]")) {
                addIssue(i + 1, "Iterate over Trigger.new/Trigger.old instead of accessing by index.");
            }
        }
    }
}
