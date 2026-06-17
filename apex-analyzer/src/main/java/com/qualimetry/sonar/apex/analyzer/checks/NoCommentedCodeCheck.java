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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

/**
 * Sections of code must not be commented out.
 */
@Rule(
        key = "qa-convention-no-commented-code",
        name = "Sections of code must not be commented out",
        description = "Detects blocks of commented-out code that should be removed rather than left in the source.",
        tags = {"convention", "unused"},
        priority = Priority.MAJOR
)
public class NoCommentedCodeCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String trimmed = lines[i].trim();
            if (trimmed.startsWith("//") && trimmed.length() > 2) {
                String afterSlash = trimmed.substring(2).trim();
                if (looksLikeCode(afterSlash)) {
                    addIssue(i + 1, "Remove commented-out code.");
                }
            }
        }
    }

    private static boolean looksLikeCode(String s) {
        int signals = 0;
        if (s.contains("=") && s.contains(";")) signals++;
        if (s.contains("(") && s.contains(")") && s.contains(";")) signals++;
        if (s.matches("^(if|for|while|return|else)\\b.*")) signals += 2;
        if (s.matches(".*\\b(insert|update|delete|upsert)\\b.*;.*")) signals++;
        return signals >= 2;
    }
}
