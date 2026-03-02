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
 * Trigger bodies must not contain business logic.
 */
@Rule(
        key = "qa-salesforce-no-trigger-logic",
        name = "Trigger bodies must not contain business logic",
        description = "Detects business logic placed directly in trigger bodies instead of delegating to handler classes.",
        tags = {"salesforce", "design"},
        priority = Priority.MAJOR
)
public class NoTriggerLogicCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (file.uri() != null && !file.uri().toString().endsWith(".trigger")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("for ") && line.contains("Trigger.") || line.contains("insert ") || line.contains("update ") || line.contains("delete ")
                    || line.contains("[SELECT") || line.contains(".addError(")) {
                addIssue(i + 1, "Move business logic out of the trigger into a handler class.");
            }
        }
    }
}
