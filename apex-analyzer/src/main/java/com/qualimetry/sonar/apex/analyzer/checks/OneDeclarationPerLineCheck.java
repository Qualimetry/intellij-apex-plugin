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
 * Declare only one variable per statement.
 */
@Rule(
        key = "qa-convention-one-declaration-per-line",
        name = "Declare only one variable per statement",
        description = "Multiple declarations on one line obscure individual variables and complicate debugging and version control diffs",
        tags = {"confusing"},
        priority = Priority.MINOR
)
public class OneDeclarationPerLineCheck extends BaseCheck {

    private static final Pattern MULTIPLE_DECL = Pattern.compile("(?:Integer|String|Boolean|Object|List|Set|Map|\\w+)\\s+\\w+\\s*=[^;]+,\\s*\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (MULTIPLE_DECL.matcher(lines[i]).find()) {
                addIssue(i + 1, "Declare only one variable per statement.");
            }
        }
    }
}
