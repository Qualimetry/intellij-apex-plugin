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
 * Local variables that are never read should be removed.
 */
@Rule(
        key = "qa-unused-local-variable",
        name = "Local variables that are never read should be removed",
        description = "Detects local variables declared but never read, indicating dead code or an incomplete refactoring.",
        tags = {"unused"},
        priority = Priority.MAJOR
)
public class UnusedLocalVariableCheck extends BaseCheck {

    private static final Pattern LOCAL_DECL = Pattern.compile("\\b(?:Integer|String|Boolean|Id|List<[^>]+>|Set<[^>]+>|Map<[^>]+>|\\w+)\\s+(\\w+)\\s*[=;]");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = normalized.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Matcher m = LOCAL_DECL.matcher(lines[i]);
            while (m.find()) {
                String varName = m.group(1);
                if (varName.equals("class") || varName.equals("new")) continue;
                String afterDecl = lines[i].substring(m.end()) + "\n";
                for (int j = i + 1; j < lines.length; j++) afterDecl += lines[j] + "\n";
                if (!afterDecl.matches("(?s).*\\b" + Pattern.quote(varName) + "\\b.*")) {
                    addIssue(i + 1, "Local variable '" + varName + "' is never read.");
                }
            }
        }
    }
}
