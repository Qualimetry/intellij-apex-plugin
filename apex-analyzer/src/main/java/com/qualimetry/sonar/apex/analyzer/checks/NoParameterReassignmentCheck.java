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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Method parameters should not be reassigned.
 */
@Rule(
        key = "qa-design-no-parameter-reassignment",
        name = "Method parameters should not be reassigned",
        description = "Reassigning a method parameter hides the original caller-supplied value, making the method harder to debug and reason about",
        tags = {"design", "misra"},
        priority = Priority.MAJOR
)
public class NoParameterReassignmentCheck extends BaseCheck {

    private static final Pattern PARAM_LIST = Pattern.compile("\\(([^)]+)\\)\\s*\\{");
    private static final Pattern PARAM = Pattern.compile("(?:final\\s+)?(\\w+)\\s+(\\w+)\\s*[,)]");
    private static final Pattern ASSIGN = Pattern.compile("\\b(\\w+)\\s*=");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher pm = PARAM_LIST.matcher(lines[i]);
            if (pm.find()) {
                Set<String> params = new HashSet<>();
                Matcher p = PARAM.matcher(pm.group(1));
                while (p.find()) params.add(p.group(2));
                int braceCount = lines[i].contains("{") ? 1 : 0;
                int start = i;
                while (start < lines.length) {
                    if (start > i) {
                        if (lines[start].contains("{")) braceCount++;
                        if (lines[start].contains("}")) braceCount--;
                    }
                    if (braceCount <= 0 && start > i) break;
                    Matcher a = ASSIGN.matcher(lines[start]);
                    while (a.find()) {
                        String id = a.group(1);
                        if (params.contains(id)) {
                            addIssue(start + 1, "Method parameters should not be reassigned.");
                            break;
                        }
                    }
                    start++;
                }
            }
        }
    }
}
