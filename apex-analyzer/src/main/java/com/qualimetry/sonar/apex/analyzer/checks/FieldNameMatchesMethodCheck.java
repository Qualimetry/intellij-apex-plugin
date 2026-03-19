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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Field names must not match method names.
 */
@Rule(
        key = "qa-convention-field-name-matches-method",
        name = "Field names must not match method names",
        description = "Identically named fields and methods force readers to rely on context to distinguish data from behavior, increasing maintenance risk",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class FieldNameMatchesMethodCheck extends BaseCheck {

    private static final Pattern METHOD = Pattern.compile("(?:public|private|protected|global)\\s+(?:static\\s+)?(?:override\\s+)?(?:\\w+\\s+)?(\\w+)\\s*\\(");
    private static final Pattern FIELD = Pattern.compile("(?:private|public|protected|global)\\s+(?:static\\s+)?(?:final\\s+)?(?:transient\\s+)?\\w+\\s+(\\w+)\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        List<String> methodNames = new ArrayList<>();
        String[] lines = prepareLines(content);
        for (String line : lines) {
            Matcher m = METHOD.matcher(line);
            if (m.find()) methodNames.add(m.group(1));
        }
        for (int i = 0; i < lines.length; i++) {
            Matcher fm = FIELD.matcher(lines[i]);
            if (fm.find() && methodNames.contains(fm.group(1))) {
                addIssue(i + 1, "Field name must not match method name '" + fm.group(1) + "'.");
            }
        }
    }
}
