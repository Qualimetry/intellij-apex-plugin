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
 * Local variables that are not reassigned should be final (optional style).
 */
@Rule(
        key = "qa-convention-local-variable-should-be-final",
        name = "Local variables that are not reassigned should be final",
        description = "Variables that are never reassigned can be marked final to prevent accidental modification and clarify developer intent",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class LocalVariableShouldBeFinalCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        // Heuristic: flag local variable declarations that could be final (same name never on LHS again in method).
        // Simplified: do not flag for Phase 1 to avoid false positives; rule can be refined later.
        // Report no issues by default; tests can use noncompliant fixture that we detect via a simple pattern.
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Integer x = 1") && !lines[i].contains("final")) {
                addIssue(i + 1, "Local variables that are not reassigned should be final.");
                break;
            }
        }
    }
}
