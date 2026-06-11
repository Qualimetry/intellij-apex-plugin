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
 * Aggregate functions require grouped SOQL queries.
 */
@Rule(
        key = "qa-salesforce-aggregate-without-group",
        name = "Aggregate functions require grouped SOQL queries",
        description = "Aggregate functions without GROUP BY produce a single result across all records, usually indicating a logic error",
        tags = {"salesforce", "performance"},
        priority = Priority.MAJOR
)
public class AggregateWithoutGroupCheck extends BaseCheck {

    private static final Pattern AGGREGATE = Pattern.compile("\\b(?:COUNT|SUM|AVG|MIN|MAX)\\s*\\(", Pattern.CASE_INSENSITIVE);
    private static final Pattern GROUP_BY = Pattern.compile("GROUP\\s+BY", Pattern.CASE_INSENSITIVE);

    @Override
    public void scan(InputFile file, String content) {
        if (!AGGREGATE.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        StringBuilder soqlBlock = new StringBuilder();
        int startLine = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("SELECT") && line.contains("FROM")) {
                soqlBlock.setLength(0);
                startLine = i + 1;
                soqlBlock.append(line);
                if (line.contains(";")) {
                    String block = soqlBlock.toString();
                    if (AGGREGATE.matcher(block).find() && !GROUP_BY.matcher(block).find()) {
                        addIssue(startLine, "Use GROUP BY when using aggregate functions in SOQL.");
                    }
                    soqlBlock.setLength(0);
                }
            } else if (soqlBlock.length() > 0) {
                soqlBlock.append(' ').append(line);
                if (line.contains(";") || line.contains(")")) {
                    String block = soqlBlock.toString();
                    if (AGGREGATE.matcher(block).find() && !GROUP_BY.matcher(block).find()) {
                        addIssue(startLine, "Use GROUP BY when using aggregate functions in SOQL.");
                    }
                    soqlBlock.setLength(0);
                }
            }
        }
    }
}
