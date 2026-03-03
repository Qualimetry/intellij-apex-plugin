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
 * Switch case labels must contain at least one statement.
 */
@Rule(
        key = "qa-error-handling-empty-switch-label",
        name = "Switch case labels must contain at least one statement",
        description = "A switch case label with no statements silently falls through or does nothing, likely indicating missing implementation",
        tags = {"unused", "suspicious"},
        priority = Priority.MINOR
)
public class EmptySwitchLabelCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ")
                .replace("\r\n", "\n").replace("\r", "\n");
        String[] lines = normalized.split("\n");
        boolean inSwitch = false;
        int switchBrace = 0;
        Integer prevCaseLine = null;  // line number (1-based) of last case/when label
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (line.contains("switch") && line.contains("(")) inSwitch = true;
            if (inSwitch) {
                boolean isCaseLine = (trimmed.startsWith("case ") || trimmed.startsWith("case\t") || trimmed.startsWith("when ") || trimmed.equals("case:") || trimmed.startsWith("when\t")) && trimmed.contains(":");
                if (isCaseLine) {
                    if (prevCaseLine != null) {
                        addIssue(prevCaseLine, "Switch case labels must contain at least one statement.");
                    }
                    prevCaseLine = i + 1;
                } else {
                    if (trimmed.length() > 0 && !trimmed.startsWith("}")) prevCaseLine = null;
                }
                switchBrace += line.chars().filter(c -> c == '{').count() - line.chars().filter(c -> c == '}').count();
                if (switchBrace <= 0) {
                    inSwitch = false;
                    prevCaseLine = null;
                }
            }
        }
    }
}
