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
 * SOQL queries should not use negative filter expressions.
 */
@Rule(
        key = "qa-salesforce-soql-negative-expression",
        name = "SOQL queries should not use negative filter expressions",
        description = "Negative filters (!=, NOT IN) often bypass index optimizations, causing full table scans on large data",
        tags = {"salesforce", "performance"},
        priority = Priority.MAJOR
)
public class SoqlNegativeExpressionCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("SELECT") || !content.contains("WHERE")) return;
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("WHERE") && (line.matches(".*\\bNOT\\b.*") || line.contains("!=") || line.contains("<>") || line.contains("= null"))) {
                addIssue(i + 1, "Avoid negative expressions in SOQL WHERE; use positive conditions for better performance.");
            }
        }
    }
}
