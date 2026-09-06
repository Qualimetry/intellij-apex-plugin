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
 * Use isEmpty() or isBlank() instead of length-based empty checks.
 */
@Rule(
        key = "qa-performance-efficient-empty-check",
        name = "Use isEmpty() or isBlank() instead of length-based empty checks",
        description = "Length-based empty checks are less readable and do not handle null safely like isEmpty() and isBlank()",
        tags = {"performance", "clumsy"},
        priority = Priority.MINOR
)
public class EfficientEmptyCheckCheck extends BaseCheck {

    private static final Pattern LENGTH_ZERO = Pattern.compile("\\.length\\s*==\\s*0|\\.size\\s*\\(\\s*\\)\\s*==\\s*0");
    private static final Pattern LENGTH_GT_ZERO = Pattern.compile("\\.length\\s*>\\s*0|\\.size\\s*\\(\\s*\\)\\s*>\\s*0");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (LENGTH_ZERO.matcher(line).find() || LENGTH_GT_ZERO.matcher(line).find()) {
                addIssue(i + 1, "Use isEmpty() or isBlank() instead of length/size comparison.");
            }
        }
    }
}
