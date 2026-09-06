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
 * SOQL queries must include WHERE or LIMIT.
 */
@Rule(
        key = "qa-salesforce-soql-missing-where-or-limit",
        name = "SOQL queries must include WHERE or LIMIT",
        description = "Unscoped queries risk retrieving all records, exhausting the 50,000-row limit and degrading performance",
        tags = {"salesforce", "performance"},
        priority = Priority.MAJOR
)
public class SoqlMissingWhereOrLimitCheck extends BaseCheck {

    private static final Pattern SELECT_START = Pattern.compile("(?i)\\[\\s*SELECT\\b");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int bracketIdx = -1;
            for (int k = 0; k < line.length(); k++) {
                if (line.charAt(k) == '[') {
                    String after = line.substring(k + 1).trim();
                    if (after.toUpperCase().startsWith("SELECT")) {
                        bracketIdx = k;
                        break;
                    }
                }
            }
            if (bracketIdx < 0) continue;

            StringBuilder soql = new StringBuilder();
            boolean closed = false;
            for (int j = i; j < lines.length; j++) {
                String segment = (j == i) ? lines[j].substring(bracketIdx) : lines[j];
                int closeIdx = segment.indexOf(']');
                if (closeIdx >= 0) {
                    soql.append(segment, 0, closeIdx);
                    closed = true;
                    break;
                }
                soql.append(segment).append(' ');
            }
            if (!closed) continue;

            String query = soql.toString().toUpperCase();
            if (query.contains("SELECT") && query.contains("FROM")
                    && !query.contains("WHERE") && !query.contains("LIMIT")) {
                addIssue(i + 1, "SOQL queries must include a WHERE clause or LIMIT to avoid full table scans.");
            }
        }
    }
}
