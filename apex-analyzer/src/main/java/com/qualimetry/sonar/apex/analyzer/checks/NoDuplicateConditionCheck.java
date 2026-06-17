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
 * Conditional branches must not repeat the same condition.
 */
@Rule(
        key = "qa-error-handling-no-duplicate-condition",
        name = "Conditional branches must not repeat the same condition",
        description = "Detects if/else-if chains or switch statements with repeated conditions, which are likely copy-paste errors.",
        tags = {"bug", "suspicious"},
        priority = Priority.MAJOR
)
public class NoDuplicateConditionCheck extends BaseCheck {

    private static final Pattern IF_COND = Pattern.compile("if\\s*\\(([^)]+)\\)");
    private static final Pattern ELSE_IF_COND = Pattern.compile("else\\s+if\\s*\\(([^)]+)\\)");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        for (int i = 0; i < normLines.length; i++) {
            String line = normLines[i];
            Matcher ifMatcher = IF_COND.matcher(line);
            if (ifMatcher.find()) {
                String cond = normalizeCond(ifMatcher.group(1));
                for (int j = i + 1; j < Math.min(i + 20, normLines.length); j++) {
                    String next = normLines[j];
                    if (next.trim().startsWith("}")) break;
                    Matcher elseIfMatcher = ELSE_IF_COND.matcher(next);
                    if (elseIfMatcher.find() && normalizeCond(elseIfMatcher.group(1)).equals(cond)) {
                        addIssue(j + 1, "Conditional branch repeats the same condition.");
                        break;
                    }
                }
            }
        }
    }

    private static String normalizeCond(String c) {
        return c.replaceAll("\\s+", " ").trim();
    }
}
