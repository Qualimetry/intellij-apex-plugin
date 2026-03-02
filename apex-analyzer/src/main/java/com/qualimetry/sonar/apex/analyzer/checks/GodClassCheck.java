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
 * Classes must not accumulate too many responsibilities.
 */
@Rule(
        key = "qa-complexity-god-class",
        name = "Classes must not accumulate too many responsibilities",
        description = "God Classes combine unrelated functionality, violating Single Responsibility and making changes risky",
        tags = {"brain-overload", "design"},
        priority = Priority.MAJOR
)
public class GodClassCheck extends BaseCheck {

    private static final int MAX_FIELDS = 15;
    private static final int MAX_METHODS = 25;
    private static final Pattern FIELD_DECL = Pattern.compile("\\b(?:private|public|protected|global)\\s+(?:static\\s+)?(?:transient\\s+)?\\w+\\s+\\w+\\s*;");
    private static final Pattern METHOD_DECL = Pattern.compile("\\b(?:private|public|protected|global)\\s+(?:static\\s+)?(?:override\\s+)?(?:testMethod\\s+)?\\w+\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String noComments = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        int fields = 0;
        var fm = FIELD_DECL.matcher(noComments);
        while (fm.find()) fields++;
        int methods = 0;
        var mm = METHOD_DECL.matcher(noComments);
        while (mm.find()) methods++;
        if (fields <= MAX_FIELDS && methods <= MAX_METHODS) return;
        if (fields > MAX_FIELDS || methods > MAX_METHODS) {
            String[] lines = prepareLines(content);
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].trim().startsWith("class ") || lines[i].contains(" class ")) {
                    StringBuilder msg = new StringBuilder("Class has too many responsibilities: ");
                    if (fields > MAX_FIELDS) msg.append(fields).append(" fields (max ").append(MAX_FIELDS).append(")");
                    if (fields > MAX_FIELDS && methods > MAX_METHODS) msg.append("; ");
                    if (methods > MAX_METHODS) msg.append(methods).append(" methods (max ").append(MAX_METHODS).append(")");
                    msg.append(".");
                    addIssue(i + 1, msg.toString());
                    return;
                }
            }
        }
    }
}
