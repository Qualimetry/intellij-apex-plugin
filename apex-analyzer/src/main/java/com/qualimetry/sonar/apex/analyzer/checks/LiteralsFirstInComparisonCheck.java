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
 * Place literal values on the left side of comparisons.
 */
@Rule(
        key = "qa-design-literals-first-in-comparison",
        name = "Place literal values on the left side of comparisons",
        description = "Placing the literal on the left side of equality checks prevents accidental NullPointerExceptions when the variable is null",
        tags = {"bad-practice"},
        priority = Priority.MAJOR
)
public class LiteralsFirstInComparisonCheck extends BaseCheck {

    private static final Pattern LITERAL_RIGHT = Pattern.compile("(?:==|!=|<>)\\s*(?:null|true|false|'[^']*'|\"[^\"]*\"|\\d+)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (LITERAL_RIGHT.matcher(lines[i]).find() && !lines[i].trim().startsWith("//")) {
                addIssue(i + 1, "Place literal values on the left side of comparisons.");
            }
        }
    }
}
