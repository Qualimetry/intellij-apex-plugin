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
 * Apex classes must declare a sharing level.
 */
@Rule(
        key = "qa-security-explicit-sharing-required",
        name = "Apex classes must declare a sharing level",
        description = "Detects Apex classes without an explicit sharing declaration (with sharing, without sharing, or inherited sharing).",
        tags = {"security", "salesforce"},
        priority = Priority.MAJOR
)
public class ExplicitSharingRequiredCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        int depth = 0;
        for (int i = 0; i < normLines.length; i++) {
            String line = normLines[i];
            if (depth == 0 && line.contains(" class ") && !line.contains("with sharing") && !line.contains("without sharing") && !line.contains("inherited sharing")) {
                addIssue(i + 1, "Apex class must declare a sharing level (with sharing, without sharing, or inherited sharing).");
            }
            depth += countChar(lines[i], '{') - countChar(lines[i], '}');
        }
    }

    private static int countChar(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) n++;
        }
        return n;
    }
}
