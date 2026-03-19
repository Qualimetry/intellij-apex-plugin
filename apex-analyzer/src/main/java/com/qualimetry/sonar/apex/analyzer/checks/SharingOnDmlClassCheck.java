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

/**
 * Classes performing DML must specify sharing declaration.
 */
@Rule(
        key = "qa-security-sharing-on-dml-class",
        name = "Classes performing DML must specify sharing declaration",
        description = "Detects classes performing DML operations that do not declare a sharing level, risking data exposure.",
        tags = {"security", "salesforce"},
        priority = Priority.MAJOR
)
public class SharingOnDmlClassCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        boolean hasDml = content.contains("insert") || content.contains("update") || content.contains("delete") || content.contains("upsert");
        if (!hasDml) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(" class ") && !lines[i].contains(" with sharing") && !lines[i].contains(" without sharing") && !lines[i].contains(" inherited sharing")) {
                addIssue(i + 1, "Class performing DML must declare a sharing level.");
                return;
            }
        }
    }
}
