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

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variable names must accurately describe their purpose (avoid single-letter or misleading names).
 */
@Rule(
        key = "qa-convention-misleading-variable-name",
        name = "Variable names must accurately describe their purpose",
        description = "Generic or single-letter variable names force readers to trace assignments to understand what the variable holds, increasing comprehension effort",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class MisleadingVariableNameCheck extends BaseCheck {

    private static final Set<String> MISLEADING = Set.of("l", "o", "I", "O", "x", "y", "a", "b", "foo", "bar", "temp", "tmp", "data", "obj");
    private static final Pattern VARIABLE = Pattern.compile("\\b(?:Integer|String|Boolean|Object|List|Set|Map|\\w+)\\s+(\\w+)\\s*[;=,) ]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = VARIABLE.matcher(lines[i]);
            while (m.find()) {
                String name = m.group(1);
                if (MISLEADING.contains(name)) {
                    addIssue(i + 1, "Variable name '" + name + "' is misleading or too short.");
                    break;
                }
            }
        }
    }
}
