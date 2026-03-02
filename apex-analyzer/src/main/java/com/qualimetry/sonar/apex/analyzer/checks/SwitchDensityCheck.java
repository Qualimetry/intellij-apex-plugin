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
 * Switch statements should not pack too many statements per case.
 */
@Rule(
        key = "qa-complexity-switch-density",
        name = "Switch should not pack too many statements per case",
        description = "Dense case blocks indicate complex logic that should be extracted into dedicated handler methods",
        tags = {"design", "brain-overload"},
        priority = Priority.MAJOR
)
public class SwitchDensityCheck extends BaseCheck {

    private static final int MAX_STATEMENTS_PER_CASE = 5;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inSwitch = false;
        int switchDepth = 0;
        int depth = 0;
        int statementsInCase = 0;
        int caseLine = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (trimmed.startsWith("switch ") || trimmed.startsWith("switch(")) {
                inSwitch = true;
                switchDepth = depth + 1;
            }
            if (inSwitch && depth >= switchDepth) {
                if (trimmed.startsWith("case ") || trimmed.startsWith("when ") || trimmed.contains(" case ") || trimmed.contains(" when ")) {
                    if (statementsInCase > MAX_STATEMENTS_PER_CASE && caseLine > 0) {
                        addIssue(caseLine, "Switch case has " + statementsInCase + " statements (max " + MAX_STATEMENTS_PER_CASE + ").");
                    }
                    statementsInCase = 0;
                    caseLine = i + 1;
                } else if (!trimmed.isEmpty() && !trimmed.startsWith("//") && !trimmed.startsWith("*") && !trimmed.equals("break;") && !trimmed.equals("}")) {
                    statementsInCase++;
                }
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (inSwitch && depth < switchDepth) {
                if (statementsInCase > MAX_STATEMENTS_PER_CASE && caseLine > 0) {
                    addIssue(caseLine, "Switch case has " + statementsInCase + " statements (max " + MAX_STATEMENTS_PER_CASE + ").");
                }
                inSwitch = false;
                statementsInCase = 0;
                caseLine = 0;
            }
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
