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
 * Field-level security checks before data access.
 * Checks each DML operation and custom-field access independently for a nearby FLS guard.
 */
@Rule(
        key = "qa-security-field-level-security",
        name = "Field-level security checks before data access",
        description = "Accessing SObject fields without FLS checks can expose data the current user is not authorized to see",
        tags = {"salesforce", "owasp-a1"},
        priority = Priority.MAJOR
)
public class FieldLevelSecurityCheck extends BaseCheck {

    private static final Pattern CUSTOM_FIELD = Pattern.compile("\\w+__c\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern DML_STATEMENT = Pattern.compile(
            "^\\s*(insert|update|delete|upsert|merge|undelete)\\s+\\w", Pattern.CASE_INSENSITIVE);
    private static final Pattern DB_DML = Pattern.compile(
            "Database\\s*\\.\\s*(insert|update|delete|upsert|merge|undelete)\\s*\\(", Pattern.CASE_INSENSITIVE);
    private static final Pattern FLS_CHECK = Pattern.compile(
            "\\b(isAccessible|isCreateable|isUpdateable|isDeletable)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            boolean needsFls = CUSTOM_FIELD.matcher(line).find()
                    || DML_STATEMENT.matcher(line).find()
                    || DB_DML.matcher(line).find();
            if (!needsFls) continue;
            if (FLS_CHECK.matcher(line).find()) continue;

            boolean flsFound = false;
            int start = Math.max(0, i - 10);
            for (int j = start; j < i; j++) {
                if (FLS_CHECK.matcher(lines[j]).find()) {
                    flsFound = true;
                    break;
                }
            }
            if (!flsFound) {
                addIssue(i + 1, "Enforce field-level security before reading or writing fields.");
            }
        }
    }
}
