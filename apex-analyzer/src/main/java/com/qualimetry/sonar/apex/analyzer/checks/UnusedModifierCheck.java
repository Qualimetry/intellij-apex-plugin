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
 * Remove unused access modifiers.
 * In Apex, interface methods are implicitly public; explicit public is redundant.
 */
@Rule(
        key = "qa-unused-modifier",
        name = "Remove unused access modifiers",
        description = "Modifiers that have no effect in context add noise without changing behavior",
        tags = {"unused"},
        priority = Priority.MINOR
)
public class UnusedModifierCheck extends BaseCheck {

    private static final Pattern PUBLIC_IN_INTERFACE = Pattern.compile("\\bpublic\\s+\\w+\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("interface ")) return;
        String[] lines = prepareLines(content);
        boolean inInterface = false;
        int depth = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().contains(" interface ")) inInterface = true;
            if (inInterface && depth == 1 && PUBLIC_IN_INTERFACE.matcher(line).find()) {
                addIssue(i + 1, "Redundant 'public' modifier; interface members are implicitly public.");
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth <= 0) inInterface = false;
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
