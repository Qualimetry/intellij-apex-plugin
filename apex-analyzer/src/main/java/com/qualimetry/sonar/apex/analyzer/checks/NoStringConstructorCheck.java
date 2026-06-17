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
 * Do not use the String constructor for string creation.
 */
@Rule(
        key = "qa-design-no-string-constructor",
        name = "Do not use the String constructor for string creation",
        description = "Using new String() creates an unnecessary object; string literals or direct assignment are more efficient and idiomatic",
        tags = {"clumsy"},
        priority = Priority.CRITICAL
)
public class NoStringConstructorCheck extends BaseCheck {

    private static final Pattern NEW_STRING = Pattern.compile("new\\s+String\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (NEW_STRING.matcher(lines[i]).find()) {
                addIssue(i + 1, "Do not use the String constructor for string creation.");
            }
        }
    }
}
