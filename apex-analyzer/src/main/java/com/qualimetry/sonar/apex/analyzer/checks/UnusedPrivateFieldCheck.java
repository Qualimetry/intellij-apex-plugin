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
 * Remove private fields that are never read.
 * Simplified: flag private fields whose name does not appear after declaration (excluding the declaration line).
 */
@Rule(
        key = "qa-unused-private-field",
        name = "Remove private fields that are never read",
        description = "Unread private fields are dead code that consumes memory and confuses maintainers",
        tags = {"unused", "cert"},
        priority = Priority.MAJOR
)
public class UnusedPrivateFieldCheck extends BaseCheck {

    private static final Pattern PRIVATE_FIELD = Pattern.compile("\\bprivate\\s+(?:static\\s+)?(?:transient\\s+)?\\w+\\s+(\\w+)\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            var m = PRIVATE_FIELD.matcher(lines[i]);
            if (m.find()) {
                String fieldName = m.group(1);
                Pattern usage = Pattern.compile("\\b" + Pattern.quote(fieldName) + "\\b");
                boolean used = false;
                for (int j = 0; j < lines.length; j++) {
                    if (j == i) continue;
                    if (usage.matcher(lines[j]).find()) {
                        used = true;
                        break;
                    }
                }
                if (!used) {
                    addIssue(i + 1, "Private field is never read; remove or use it.");
                }
            }
        }
    }
}
