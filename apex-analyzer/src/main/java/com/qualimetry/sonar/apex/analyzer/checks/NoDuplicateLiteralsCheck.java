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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * String literals must not be duplicated.
 */
@Rule(
        key = "qa-convention-no-duplicate-literals",
        name = "String literals must not be duplicated",
        description = "Detects string literals that appear multiple times and should be extracted into named constants.",
        tags = {"convention"},
        priority = Priority.MAJOR
)
public class NoDuplicateLiteralsCheck extends BaseCheck {

    private static final Pattern STRING_LITERAL = Pattern.compile("\"([^\"]{3,})\"");
    private static final int MIN_LENGTH = 5;
    private static final int MIN_OCCURRENCES = 2;

    @Override
    public void scan(InputFile file, String content) {
        Map<String, Integer> literalCount = new HashMap<>();
        Map<String, Integer> literalFirstLine = new HashMap<>();
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            Matcher m = STRING_LITERAL.matcher(line);
            while (m.find()) {
                String lit = m.group(1);
                if (lit.length() >= MIN_LENGTH) {
                    literalCount.merge(lit, 1, Integer::sum);
                    literalFirstLine.putIfAbsent(lit, i + 1);
                }
            }
        }
        for (Map.Entry<String, Integer> e : literalCount.entrySet()) {
            if (e.getValue() >= MIN_OCCURRENCES) {
                addIssue(literalFirstLine.get(e.getKey()), "Duplicate string literal \"" + e.getKey() + "\" (" + e.getValue() + " times).");
            }
        }
    }
}
