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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Batch Apex scope should not exceed 2000 records.
 */
@Rule(
        key = "qa-salesforce-batch-scope-limit",
        name = "Batch Apex scope should not exceed 2000 records",
        description = "Flags Batch Apex queries with LIMIT above 2000, which can cause heap or CPU limit exceptions during processing.",
        tags = {"salesforce", "governor-limits", "batch"},
        priority = Priority.MAJOR
)
public class BatchScopeLimitCheck extends BaseCheck {

    private static final Pattern DATABASE_GETQUERYLOCATOR = Pattern.compile("Database\\.getQueryLocator\\s*\\(");
    private static final Pattern LIMIT_OVER_2000 = Pattern.compile("LIMIT\\s+(2[1-9]\\d{2}|[3-9]\\d{3}|\\d{5,})", Pattern.CASE_INSENSITIVE);

    @Override
    public void scan(InputFile file, String content) {
        if (!DATABASE_GETQUERYLOCATOR.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = LIMIT_OVER_2000.matcher(lines[i]);
            if (m.find()) {
                addIssue(i + 1, "Batch Apex scope should not exceed 2000 records.");
                return;
            }
        }
    }
}
