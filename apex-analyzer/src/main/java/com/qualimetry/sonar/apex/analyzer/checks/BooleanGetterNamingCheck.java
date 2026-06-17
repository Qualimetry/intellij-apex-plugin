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
 * Boolean getters should use 'is' or 'get' prefix convention.
 */
@Rule(
        key = "qa-convention-boolean-getter-naming",
        name = "Boolean getters should use 'is' prefix convention",
        description = "Boolean getters named getXxx instead of isXxx read unnaturally in conditions and violate the established naming convention for boolean accessors",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class BooleanGetterNamingCheck extends BaseCheck {

    private static final Pattern BOOLEAN_GETTER = Pattern.compile("(?:public|global)\\s+(?:static\\s+)?Boolean\\s+(get\\w+)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = BOOLEAN_GETTER.matcher(lines[i]);
            if (m.find()) {
                String name = m.group(1);
                if (!name.startsWith("is") && name.startsWith("get")) {
                    addIssue(i + 1, "Boolean getter '" + name + "' should use 'is' prefix (e.g. isActive).");
                }
            }
        }
    }
}
