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
 * Switch must not have too many case clauses.
 */
@Rule(
        key = "qa-complexity-switch-case-limit",
        name = "Switch must not have too many case clauses",
        description = "Detects switch statements with more case clauses than the configured limit, suggesting refactoring.",
        tags = {"complexity", "brain-overload"},
        priority = Priority.MAJOR
)
public class SwitchCaseLimitCheck extends BaseCheck {

    private static final int MAX_CASES = 10;
    private static final Pattern WHEN_PATTERN = Pattern.compile("\\bwhen\\s+");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        int i = 0;
        while (i < normLines.length) {
            if (normLines[i].contains(" switch ")) {
                int startLine = i + 1;
                int depth = 0;
                int caseCount = 0;
                for (int j = i; j < normLines.length; j++) {
                    String l = normLines[j];
                    depth += countChar(l, '{') - countChar(l, '}');
                    Matcher m = WHEN_PATTERN.matcher(l);
                    while (m.find()) caseCount++;
                    if (depth < 0) break;
                }
                if (caseCount > MAX_CASES) {
                    addIssue(startLine, "Switch has " + caseCount + " when clauses (max " + MAX_CASES + ").");
                }
                i++;
            } else {
                i++;
            }
        }
    }

    private static int countChar(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) n++;
        }
        return n;
    }
}
