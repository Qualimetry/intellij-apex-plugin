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
 * Classes must not declare too many fields.
 */
@Rule(
        key = "qa-complexity-too-many-fields",
        name = "Classes must not declare too many fields",
        description = "High field counts increase cognitive load and expand the state space that must be tested",
        tags = {"brain-overload"},
        priority = Priority.MAJOR
)
public class TooManyFieldsCheck extends BaseCheck {

    private static final int MAX_FIELDS = 20;
    private static final Pattern FIELD = Pattern.compile("\\b(?:private|public|protected|global)\\s+(?:static\\s+)?(?:transient\\s+)?\\w+\\s+\\w+\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String noComments = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        int count = 0;
        var m = FIELD.matcher(noComments);
        while (m.find()) count++;
        if (count <= MAX_FIELDS) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().startsWith("class ") || lines[i].contains(" class ")) {
                addIssue(i + 1, "Class has too many fields (" + count + ", max " + MAX_FIELDS + ").");
                return;
            }
        }
    }
}
