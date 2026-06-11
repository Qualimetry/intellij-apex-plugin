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
 * Dynamic type reflection requires security review.
 */
@Rule(
        key = "qa-security-type-reflection-review",
        name = "Dynamic type reflection requires security review",
        description = "Unvalidated Type.forName() calls can instantiate arbitrary classes, enabling unintended code execution",
        tags = {"cwe", "owasp-a3"},
        priority = Priority.MAJOR
)
public class TypeReflectionReviewCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Type.forName")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Type.forName")) {
                addIssue(i + 1, "Review dynamic type reflection for security impact.");
            }
        }
    }
}
