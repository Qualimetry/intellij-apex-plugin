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
 * Switch statements must have a minimum number of branches.
 */
@Rule(
        key = "qa-design-switch-min-branches",
        name = "Switch statements must have a minimum number of branches",
        description = "A switch statement with fewer than three branches is better expressed as an if-else chain for clarity",
        tags = {"convention", "performance"},
        priority = Priority.MINOR
)
public class SwitchMinBranchesCheck extends BaseCheck {

    private static final int MIN_BRANCHES = 3;
    private static final Pattern SWITCH = Pattern.compile("\\bswitch\\s*\\(");
    private static final Pattern WHEN_OR_CASE = Pattern.compile("\\b(when|case)\\s+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inSwitch = false;
        int braceCount = 0;
        int branchCount = 0;
        int switchLine = -1;
        for (int i = 0; i < lines.length; i++) {
            if (SWITCH.matcher(lines[i]).find()) {
                inSwitch = true;
                switchLine = i + 1;
                branchCount = 0;
                braceCount = 0;
            }
            if (inSwitch) {
                if (WHEN_OR_CASE.matcher(lines[i]).find()) branchCount++;
                if (lines[i].contains("{")) braceCount++;
                if (lines[i].contains("}")) braceCount--;
                if (braceCount <= 0 && i >= switchLine) {
                    if (branchCount > 0 && branchCount < MIN_BRANCHES) {
                        addIssue(switchLine, "Switch statements must have a minimum number of branches.");
                    }
                    inSwitch = false;
                }
            }
        }
    }
}
