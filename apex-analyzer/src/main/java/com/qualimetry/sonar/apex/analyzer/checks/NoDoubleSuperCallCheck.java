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
 * Constructors must not invoke super() more than once.
 */
@Rule(
        key = "qa-design-no-double-super-call",
        name = "Constructors must not invoke super() more than once",
        description = "Calling super() multiple times in a constructor is illogical and indicates a misunderstanding of the constructor chain",
        tags = {"confusing"},
        priority = Priority.MINOR
)
public class NoDoubleSuperCallCheck extends BaseCheck {

    private static final Pattern SUPER_CALL = Pattern.compile("\\bsuper\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int braceCount = 0;
        int superCount = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("{")) braceCount++;
            if (braceCount >= 1 && SUPER_CALL.matcher(line).find()) {
                superCount++;
                if (superCount > 1) {
                    addIssue(i + 1, "Constructors must not invoke super() more than once.");
                    superCount = 0;
                }
            }
            if (line.contains("}")) braceCount--;
            if (braceCount <= 0) superCount = 0;
        }
    }
}
