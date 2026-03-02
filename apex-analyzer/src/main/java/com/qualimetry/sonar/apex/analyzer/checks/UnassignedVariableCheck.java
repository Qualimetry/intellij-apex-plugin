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
 * Variables must be assigned before use.
 * Simplified: flag variables that appear on RHS of assignment or in expression before any assignment to them.
 */
@Rule(
        key = "qa-design-unassigned-variable",
        name = "Variables must be assigned before use",
        description = "Uninitialized variables contain null, leading to NullPointerException at runtime",
        tags = {"design"},
        priority = Priority.INFO
)
public class UnassignedVariableCheck extends BaseCheck {

    private static final Pattern DECL_NO_INIT = Pattern.compile("\\b(?:Integer|String|Boolean|Long|Double|Decimal|Id|List<[^>]+>|Set<[^>]+>|Map<[^>]+>)\\s+(\\w+)\\s*;");
    private static final Pattern SIMPLE_ASSIGN = Pattern.compile("\\b(\\w+)\\s*=");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            var dm = DECL_NO_INIT.matcher(lines[i]);
            if (dm.find()) {
                String varName = dm.group(1);
                boolean assigned = false;
                for (int j = i + 1; j < lines.length; j++) {
                    String l = lines[j];
                    if (SIMPLE_ASSIGN.matcher(l).find() && l.trim().startsWith(varName + " =")) assigned = true;
                    if (assigned) break;
                    if (l.contains(varName) && !l.trim().startsWith("//")) {
                        if (!l.trim().startsWith(varName + " ") && l.contains("=") && !l.contains(varName + " =")) {
                            addIssue(j + 1, "Variable '" + varName + "' may be used before assignment.");
                            break;
                        }
                    }
                }
            }
        }
    }
}
