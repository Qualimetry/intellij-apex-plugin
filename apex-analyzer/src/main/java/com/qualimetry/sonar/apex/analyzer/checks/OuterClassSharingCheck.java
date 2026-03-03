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
 * Outer classes with SOQL or DML must declare sharing level.
 */
@Rule(
        key = "qa-security-outer-class-sharing",
        name = "Outer classes with SOQL or DML must declare sharing level",
        description = "Detects outer classes containing SOQL or DML that lack an explicit sharing declaration.",
        tags = {"security", "salesforce"},
        priority = Priority.MAJOR
)
public class OuterClassSharingCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        boolean hasSoqlOrDml = content.contains("SELECT") || content.contains("INSERT") || content.contains("UPDATE") || content.contains("DELETE") || content.contains("UPSERT");
        if (!hasSoqlOrDml) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains(" class ") && !line.contains(" with sharing") && !line.contains(" without sharing") && !line.contains(" inherited sharing")) {
                addIssue(i + 1, "Outer class with SOQL or DML must declare a sharing level.");
                return;
            }
        }
    }
}
