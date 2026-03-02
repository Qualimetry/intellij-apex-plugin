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

import java.util.regex.Pattern;

/**
 * Dynamic SOQL must escape all user-supplied input.
 */
@Rule(
        key = "qa-security-escape-dynamic-soql",
        name = "Dynamic SOQL must escape all user-supplied input",
        description = "Unescaped user input in dynamic SOQL enables injection attacks that can bypass record-level security",
        tags = {"cwe"},
        priority = Priority.MAJOR
)
public class EscapeDynamicSoqlCheck extends BaseCheck {

    private static final Pattern SOQL_STRING = Pattern.compile("(?i)'\\s*SELECT\\b");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!SOQL_STRING.matcher(lines[i]).find()) continue;

            StringBuilder stmt = new StringBuilder();
            for (int j = i; j < lines.length; j++) {
                stmt.append(lines[j]);
                if (lines[j].contains(";")) break;
                stmt.append(' ');
            }
            String full = stmt.toString();
            if (full.contains("+")
                    && !full.contains("String.escapeSingleQuotes")
                    && !full.contains("String.valueOf")) {
                addIssue(i + 1, "Escape user input with String.escapeSingleQuotes() in dynamic SOQL.");
            }
        }
    }
}
