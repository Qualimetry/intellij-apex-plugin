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
 * Type casts must be guarded by instanceof checks.
 */
@Rule(
        key = "qa-error-handling-guard-type-cast",
        name = "Type casts must be guarded by instanceof checks",
        description = "Casting without an instanceof guard risks a TypeException at runtime if the object is not of the expected type",
        tags = {"cwe"},
        priority = Priority.MAJOR
)
public class GuardTypeCastCheck extends BaseCheck {

    private static final Pattern CAST_PATTERN = Pattern.compile("\\(\\s*([A-Z]\\w*)\\s*\\)\\s*([a-zA-Z_]\\w*)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            var m = CAST_PATTERN.matcher(line);
            if (!m.find()) continue;
            String castType = m.group(1);
            String varName = m.group(2);
            int methodStart = findEnclosingBraceStart(lines, i);
            boolean guarded = false;
            for (int j = methodStart; j < i; j++) {
                if (lines[j].contains("instanceof " + castType)
                        || lines[j].contains(varName + " instanceof ")) {
                    guarded = true;
                    break;
                }
            }
            if (!guarded) {
                addIssue(i + 1, "Type casts must be guarded by instanceof checks.");
            }
        }
    }

    private static int findEnclosingBraceStart(String[] lines, int lineIndex) {
        int braceDepth = 0;
        for (int i = lineIndex; i >= 0; i--) {
            String line = lines[i];
            for (int j = line.length() - 1; j >= 0; j--) {
                char ch = line.charAt(j);
                if (ch == '}') {
                    braceDepth++;
                } else if (ch == '{') {
                    braceDepth--;
                    if (braceDepth < 0) return i;
                }
            }
        }
        return 0;
    }
}
