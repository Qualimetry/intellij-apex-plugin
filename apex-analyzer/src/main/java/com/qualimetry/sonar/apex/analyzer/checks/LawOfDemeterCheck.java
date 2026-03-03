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
 * Limit chained method calls to reduce coupling.
 */
@Rule(
        key = "qa-design-law-of-demeter",
        name = "Limit chained method calls to reduce coupling",
        description = "Deeply chained method calls violate the Law of Demeter, creating tight coupling that makes refactoring difficult when intermediate APIs change",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class LawOfDemeterCheck extends BaseCheck {

    private static final Pattern CHAIN = Pattern.compile("\\.\\w+\\s*\\([^)]*\\)\\s*\\.\\w+\\s*\\([^)]*\\)\\s*\\.");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (CHAIN.matcher(lines[i]).find()) {
                addIssue(i + 1, "Limit chained method calls to reduce coupling.");
            }
        }
    }
}
