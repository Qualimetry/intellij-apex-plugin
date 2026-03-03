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

import java.util.regex.Pattern;

/**
 * Stateful Batch should not store Database.SaveResult.
 */
@Rule(
        key = "qa-error-stateful-database-result",
        name = "Stateful Batch should not store Database.SaveResult",
        description = "Flags Database.Stateful batch classes that store Database.SaveResult, which is not serializable between execute invocations.",
        tags = {"error-prone", "salesforce", "batch"},
        priority = Priority.MAJOR
)
public class StatefulDatabaseResultCheck extends BaseCheck {

    private static final Pattern IMPLEMENTS_BATCH = Pattern.compile("implements\\s+Database\\.Batchable");
    private static final Pattern STATEFUL = Pattern.compile("implements\\s+.*Stateful\\b");
    private static final Pattern SAVE_RESULT_FIELD = Pattern.compile("(List<)?Database\\.SaveResult|Database\\.SaveResult\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        if (!IMPLEMENTS_BATCH.matcher(content).find()) return;
        if (!STATEFUL.matcher(content).find()) return;
        if (!SAVE_RESULT_FIELD.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (SAVE_RESULT_FIELD.matcher(lines[i]).find()) {
                addIssue(i + 1, "Stateful Batch should not store Database.SaveResult; state is serialized between batches.");
                return;
            }
        }
    }
}
