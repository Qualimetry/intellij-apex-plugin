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
 * Override both equals() and hashCode().
 */
@Rule(
        key = "qa-error-equals-without-hashcode",
        name = "Override both equals() and hashCode()",
        description = "Flags classes that override equals() without hashCode() or vice versa, breaking the hash contract for collections.",
        tags = {"error-prone", "cert"},
        priority = Priority.MAJOR
)
public class EqualsWithoutHashcodeCheck extends BaseCheck {

    private static final Pattern EQUALS_METHOD = Pattern.compile("\\b(Boolean|boolean)\\s+equals\\s*\\(");
    private static final Pattern HASHCODE_METHOD = Pattern.compile("\\b(Integer|int)\\s+hashCode\\s*\\(");
    private static final Pattern CLASS_DECL = Pattern.compile("(?i)\\bclass\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean hasEquals = false;
        boolean hasHashCode = false;
        int classLine = -1;
        for (int i = 0; i < lines.length; i++) {
            if (classLine < 0 && CLASS_DECL.matcher(lines[i]).find()) {
                classLine = i;
            }
            if (EQUALS_METHOD.matcher(lines[i]).find()) hasEquals = true;
            if (HASHCODE_METHOD.matcher(lines[i]).find()) hasHashCode = true;
        }
        if (classLine >= 0 && hasEquals != hasHashCode) {
            addIssue(classLine + 1, "Override both equals() and hashCode() together.");
        }
    }
}
