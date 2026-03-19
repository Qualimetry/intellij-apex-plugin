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
 * Check CRUD permissions before DML operations.
 */
@Rule(
        key = "qa-security-crud-violation",
        name = "Check CRUD permissions before DML",
        description = "Flags DML operations not preceded by CRUD permission checks, risking unauthorized data modification.",
        tags = {"security", "salesforce", "cwe"},
        priority = Priority.CRITICAL
)
public class CrudViolationCheck extends BaseCheck {

    private static final Pattern DML = Pattern.compile(
            "\\b(insert|update|delete|upsert)\\s+|\\bDatabase\\.(insert|update|delete|upsert)\\s*\\("
    );
    private static final Pattern CRUD_CHECK = Pattern.compile(
            "is(Createable|Updateable|Deletable|Accessible|Upsertable)\\s*\\(\\)|Schema\\.sObjectType|\\.getDescribe\\s*\\("
    );

    private static final int LOOKBACK = 15;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!DML.matcher(lines[i]).find()) continue;
            boolean guarded = false;
            int start = Math.max(0, i - LOOKBACK);
            for (int j = start; j < i; j++) {
                if (CRUD_CHECK.matcher(lines[j]).find()) {
                    guarded = true;
                    break;
                }
            }
            if (!guarded) {
                addIssue(i + 1, "Check CRUD permissions before DML operations.");
            }
        }
    }
}
