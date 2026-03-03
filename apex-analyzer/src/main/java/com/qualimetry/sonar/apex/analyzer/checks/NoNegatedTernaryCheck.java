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
 * Ternary expressions should not use negated conditions.
 */
@Rule(
        key = "qa-design-no-negated-ternary",
        name = "Ternary expressions should not use negated conditions",
        description = "Negated conditions in ternary expressions force readers to mentally invert the logic, reducing clarity",
        tags = {"clumsy"},
        priority = Priority.MAJOR
)
public class NoNegatedTernaryCheck extends BaseCheck {

    private static final Pattern NEGATED_TERNARY = Pattern.compile("\\?\\s*!\\s*[^?]+\\?");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (NEGATED_TERNARY.matcher(lines[i]).find()) {
                addIssue(i + 1, "Ternary expressions should not use negated conditions.");
            }
        }
    }
}
