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
 * Remove null checks that are unreachable in equals methods.
 * If equals() first does getClass() != o.getClass() return false, then (o == null) is unreachable.
 */
@Rule(
        key = "qa-unused-null-check-in-equals",
        name = "Remove unreachable null checks in equals",
        description = "Unreachable null checks in equals() are dead code that clutters the method and misleads maintainers",
        tags = {"unused"},
        priority = Priority.MINOR
)
public class UnusedNullCheckInEqualsCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("equals(") || !content.contains("getClass()")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("getClass()") && (line.contains("!= o.getClass()") || line.contains("!= obj.getClass()"))) {
                for (int j = i + 1; j < Math.min(i + 15, lines.length); j++) {
                    String l = lines[j].trim();
                    if (l.contains("== null") || l.contains("== null)")) {
                        addIssue(j + 1, "Null check is unreachable after getClass() check in equals().");
                        return;
                    }
                    if (l.startsWith("return ") || l.equals("}")) break;
                }
            }
        }
    }
}
