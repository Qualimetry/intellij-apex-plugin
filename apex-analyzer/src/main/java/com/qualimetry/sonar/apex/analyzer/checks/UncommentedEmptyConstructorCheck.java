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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Pattern;

/**
 * Empty constructors should include a justification comment.
 */
@Rule(
        key = "qa-design-uncommented-empty-constructor",
        name = "Empty constructors should include a justification comment",
        description = "An empty constructor with no comment suggests forgotten initialization logic; add a comment explaining why it is intentionally empty",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class UncommentedEmptyConstructorCheck extends BaseCheck {

    private static final Pattern EMPTY_CONSTRUCTOR = Pattern.compile("\\w+\\s*\\([^)]*\\)\\s*\\{\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (EMPTY_CONSTRUCTOR.matcher(lines[i].replaceAll("\\s+", " ")).find()) {
                boolean hasComment = i > 0 && (lines[i - 1].trim().startsWith("//") || lines[i - 1].trim().startsWith("/*"));
                if (!hasComment) {
                    addIssue(i + 1, "Empty constructors should include a justification comment.");
                }
            }
        }
    }
}
