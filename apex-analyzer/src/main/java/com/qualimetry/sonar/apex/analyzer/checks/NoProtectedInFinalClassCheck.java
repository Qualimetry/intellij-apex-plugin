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
 * Final classes should not contain protected fields.
 */
@Rule(
        key = "qa-design-no-protected-in-final-class",
        name = "Final classes should not contain protected fields",
        description = "Protected members in a final class are misleading because no subclass can ever exist to use that access level",
        tags = {"design", "confusing"},
        priority = Priority.MINOR
)
public class NoProtectedInFinalClassCheck extends BaseCheck {

    private static final Pattern FINAL_CLASS = Pattern.compile("\\bfinal\\s+class\\s+\\w+");
    private static final Pattern PROTECTED_FIELD = Pattern.compile("\\bprotected\\s+(?:static\\s+)?(?:final\\s+)?\\w+\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inFinalClass = false;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            if (FINAL_CLASS.matcher(lines[i]).find()) inFinalClass = true;
            if (inFinalClass) {
                if (lines[i].contains("{")) braceCount++;
                if (PROTECTED_FIELD.matcher(lines[i]).find()) {
                    addIssue(i + 1, "Final classes should not contain protected fields.");
                }
                if (lines[i].contains("}")) braceCount--;
                if (braceCount == 0) inFinalClass = false;
            }
        }
    }
}
