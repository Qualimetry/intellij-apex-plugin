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
 * Place field declarations at the top of the class.
 */
@Rule(
        key = "qa-convention-field-declaration-position",
        name = "Place field declarations at the top of the class",
        description = "Fields interleaved among methods break the expected reading order and force readers to hunt for the class state",
        tags = {"convention", "bad-practice"},
        priority = Priority.MINOR
)
public class FieldDeclarationPositionCheck extends BaseCheck {

    private static final Pattern METHOD = Pattern.compile("(?:public|private|protected|global)\\s+(?:static\\s+)?(?:override\\s+)?(?:\\w+\\s+)?\\w+\\s*\\(");
    private static final Pattern FIELD = Pattern.compile("(?:private|public|protected|global)\\s+(?:static\\s+)?(?:final\\s+)?(?:transient\\s+)?\\w+\\s+\\w+\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean seenMethod = false;
        for (int i = 0; i < lines.length; i++) {
            if (METHOD.matcher(lines[i]).find()) seenMethod = true;
            if (seenMethod && FIELD.matcher(lines[i]).find()) {
                addIssue(i + 1, "Place field declarations at the top of the class.");
            }
        }
    }
}
