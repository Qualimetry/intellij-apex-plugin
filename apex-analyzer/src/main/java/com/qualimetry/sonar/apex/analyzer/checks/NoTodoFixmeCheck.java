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
 * Resolve TODO and FIXME comments before release.
 */
@Rule(
        key = "qa-convention-no-todo-fixme",
        name = "Resolve TODO and FIXME comments before release",
        description = "Detects TODO and FIXME comments that should be resolved and removed before release.",
        tags = {"convention", "pitfall"},
        priority = Priority.INFO
)
public class NoTodoFixmeCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String upper = lines[i].toUpperCase();
            if (upper.contains("TODO") || upper.contains("FIXME")) {
                addIssue(i + 1, "Resolve TODO/FIXME before release.");
            }
        }
    }
}
