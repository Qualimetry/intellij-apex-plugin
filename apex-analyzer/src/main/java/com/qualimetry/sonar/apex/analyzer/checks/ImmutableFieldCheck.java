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
 * Fields that are never reassigned should be declared final.
 */
@Rule(
        key = "qa-design-immutable-field",
        name = "Fields that are never reassigned should be declared final",
        description = "Declaring never-reassigned fields as final communicates immutability intent and prevents accidental modification",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class ImmutableFieldCheck extends BaseCheck {

    private static final Pattern FIELD_DECL = Pattern.compile("(?:private|protected|public|global)\\s+(?:static\\s+)?(?!final)(\\w+)\\s+(\\w+)\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = FIELD_DECL.matcher(lines[i]);
            if (m.find() && !lines[i].trim().startsWith("//")) {
                String fieldName = m.group(2);
                Pattern assign = Pattern.compile(
                        "\\b" + Pattern.quote(fieldName) + "\\s*(?:[+\\-*/&|^])?=(?!=)");
                boolean reassigned = false;
                for (int j = i + 1; j < lines.length; j++) {
                    if (assign.matcher(lines[j]).find()) {
                        reassigned = true;
                        break;
                    }
                }
                if (!reassigned) {
                    addIssue(i + 1, "Fields that are never reassigned should be declared final.");
                }
            }
        }
    }
}
