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
 * Non-final static fields should not be assigned after initialization.
 */
@Rule(
        key = "qa-design-no-nonfinal-static-assignment",
        name = "Non-final static fields should not be assigned after initialization",
        description = "Reassigning non-final static fields after declaration creates shared mutable state that is hard to reason about and prone to unexpected side effects",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class NoNonfinalStaticAssignmentCheck extends BaseCheck {

    private static final Pattern STATIC_FIELD = Pattern.compile("\\bstatic\\s+(?!final\\s)(\\w+)\\s+(\\w+)\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = STATIC_FIELD.matcher(lines[i]);
            if (m.find()) {
                String fieldName = m.group(2);
                for (int j = i + 1; j < lines.length; j++) {
                    if (lines[j].trim().startsWith("}")) break;
                    if (Pattern.compile("\\b" + Pattern.quote(fieldName) + "\\s*=").matcher(lines[j]).find()
                            && !lines[j].trim().startsWith("//")) {
                        addIssue(j + 1, "Non-final static field '" + fieldName + "' should not be assigned after initialization.");
                        break;
                    }
                }
            }
        }
    }
}
