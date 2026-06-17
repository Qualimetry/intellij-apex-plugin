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
 * Only one trigger should exist per SObject.
 */
@Rule(
        key = "qa-salesforce-one-trigger-per-object",
        name = "Only one trigger should exist per SObject",
        description = "Multiple triggers on the same object execute in an unpredictable order, complicating logic and debugging",
        tags = {"salesforce"},
        priority = Priority.MAJOR
)
public class OneTriggerPerObjectCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("trigger ") || !content.contains(" on ")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().startsWith("trigger ") && lines[i].contains(" on ")) {
                addIssue(i + 1, "Only one trigger per SObject is recommended; consolidate triggers for the same object.");
                return;
            }
        }
    }
}
