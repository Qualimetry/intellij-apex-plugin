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
 * Enforce naming standards for abstract classes (e.g. must start with Abstract or Base).
 */
@Rule(
        key = "qa-convention-abstract-naming",
        name = "Enforce naming standards for abstract classes",
        description = "Inconsistently named abstract classes make it harder to distinguish base types from concrete implementations at a glance",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class AbstractNamingCheck extends BaseCheck {

    private static final Pattern ABSTRACT_CLASS = Pattern.compile("\\babstract\\s+class\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = ABSTRACT_CLASS.matcher(lines[i]);
            if (m.find()) {
                String name = m.group(1);
                if (!name.startsWith("Abstract") && !name.startsWith("Base")) {
                    addIssue(i + 1, "Abstract class '" + name + "' should start with Abstract or Base.");
                }
            }
        }
    }
}
