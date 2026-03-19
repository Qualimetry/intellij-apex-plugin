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
 * Private methods that are never called should be removed.
 */
@Rule(
        key = "qa-unused-private-method",
        name = "Private methods that are never called should be removed",
        description = "Detects private methods that are never called within the declaring class, representing dead code.",
        tags = {"unused"},
        priority = Priority.MAJOR
)
public class UnusedPrivateMethodCheck extends BaseCheck {

    private static final Pattern PRIVATE_METHOD = Pattern.compile("\\bprivate\\s+(?:static\\s+)?(?:\\w+\\s+)?(\\w+)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = normalized.split("\n");
        StringBuilder full = new StringBuilder();
        for (String l : lines) full.append(l).append("\n");
        String fullNorm = full.toString();
        for (int i = 0; i < lines.length; i++) {
            Matcher m = PRIVATE_METHOD.matcher(lines[i]);
            while (m.find()) {
                String methodName = m.group(1);
                if (methodName.equals("class")) continue;
                int lineStart = 0;
                for (int j = 0; j < i; j++) lineStart += lines[j].length() + 1;
                int declEnd = lineStart + lines[i].length() + 1;
                String before = fullNorm.substring(0, lineStart + lines[i].indexOf(methodName));
                String after = fullNorm.substring(declEnd);
                String rest = before + after;
                if (!rest.matches("(?s).*\\b" + Pattern.quote(methodName) + "\\s*\\(.*")) {
                    addIssue(i + 1, "Private method '" + methodName + "' is never called.");
                }
            }
        }
    }
}
