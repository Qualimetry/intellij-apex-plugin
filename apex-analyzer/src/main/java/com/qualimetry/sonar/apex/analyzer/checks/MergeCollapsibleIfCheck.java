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
 * Consecutive if statements with no else should be merged.
 */
@Rule(
        key = "qa-design-merge-collapsible-if",
        name = "Consecutive if statements with no else should be merged",
        description = "Detects consecutive if statements without else clauses that can be combined into a single if with a compound condition.",
        tags = {"design", "clumsy"},
        priority = Priority.MAJOR
)
public class MergeCollapsibleIfCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length - 1; i++) {
            String curr = lines[i].trim();
            String next = lines[i + 1].trim();
            if ((curr.startsWith("if ") || curr.startsWith("if(")) && curr.endsWith("{")) {
                if ((next.startsWith("if ") || next.startsWith("if(")) && !curr.contains(" else ")) {
                    addIssue(i + 2, "Merge this if with the previous one using &&.");
                }
            }
        }
    }
}
