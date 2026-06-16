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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replace element-by-element loops with addAll().
 */
@Rule(
        key = "qa-design-use-addall",
        name = "Replace element-by-element loops with addAll()",
        description = "Looping over a collection only to call .add() on each element is verbose and less efficient than a single addAll() call",
        tags = {"clumsy"},
        priority = Priority.MINOR
)
public class UseAddallCheck extends BaseCheck {

    private static final Pattern FOR_LOOP = Pattern.compile("\\bfor\\s*\\(");
    private static final Pattern ADD_IN_BODY = Pattern.compile("\\.add\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int braceCount = 0;
        boolean inFor = false;
        int forLine = -1;
        for (int i = 0; i < lines.length; i++) {
            if (FOR_LOOP.matcher(lines[i]).find()) {
                inFor = true;
                forLine = i + 1;
                braceCount = 0;
            }
            if (inFor) {
                if (lines[i].contains("{")) braceCount++;
                if (braceCount >= 1 && ADD_IN_BODY.matcher(lines[i]).find()) {
                    addIssue(forLine, "Replace element-by-element loop with addAll().");
                    inFor = false;
                }
                if (lines[i].contains("}")) braceCount--;
                if (braceCount <= 0) inFor = false;
            }
        }
    }
}
