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

/**
 * Switch statements must include when-else clause.
 */
@Rule(
        key = "qa-design-switch-when-else",
        name = "Switch statements must include when-else clause",
        description = "Detects switch statements without a when-else (default) clause, leaving unhandled values.",
        tags = {"design", "cert"},
        priority = Priority.MAJOR
)
public class SwitchWhenElseCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        if (!normalized.contains(" switch ")) return;
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        for (int i = 0; i < normLines.length; i++) {
            if (normLines[i].contains(" switch ")) {
                int depth = 0;
                boolean hasWhenElse = false;
                for (int j = i; j < normLines.length; j++) {
                    String l = normLines[j];
                    depth += countChar(l, '{') - countChar(l, '}');
                    if (l.contains(" when else ") || l.contains(" when else{")) {
                        hasWhenElse = true;
                        break;
                    }
                    if (depth < 0) break;
                }
                if (!hasWhenElse) {
                    addIssue(i + 1, "Switch statement must include a when-else clause.");
                }
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
