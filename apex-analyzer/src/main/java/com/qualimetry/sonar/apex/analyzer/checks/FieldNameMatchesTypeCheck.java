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
 * Field names must not match the enclosing type name.
 */
@Rule(
        key = "qa-convention-field-name-matches-type",
        name = "Field names must not match the enclosing type name",
        description = "A field sharing its enclosing class name creates confusing references and makes constructors and method bodies harder to read",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class FieldNameMatchesTypeCheck extends BaseCheck {

    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+(\\w+)");
    private static final Pattern FIELD = Pattern.compile("(?:private|public|protected|global)\\s+(?:static\\s+)?(?:final\\s+)?(?:transient\\s+)?\\w+\\s+(\\w+)\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        Matcher cn = CLASS_NAME.matcher(content);
        if (!cn.find()) return;
        String typeName = cn.group(1);
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher fm = FIELD.matcher(lines[i]);
            if (fm.find() && typeName.equals(fm.group(1))) {
                addIssue(i + 1, "Field name must not match the enclosing type name '" + typeName + "'.");
            }
        }
    }
}
