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
 * Use isEmpty() to check collection emptiness.
 */
@Rule(
        key = "qa-performance-use-collection-isempty",
        name = "Use isEmpty() for collection emptiness",
        description = "size() == 0 is less expressive than isEmpty() and can obscure intent in conditional checks",
        tags = {"clumsy"},
        priority = Priority.MINOR
)
public class UseCollectionIsemptyCheck extends BaseCheck {

    private static final Pattern SIZE_ZERO = Pattern.compile("\\.size\\s*\\(\\s*\\)\\s*==\\s*0");
    private static final Pattern SIZE_GT_ZERO = Pattern.compile("\\.size\\s*\\(\\s*\\)\\s*>\\s*0");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (SIZE_ZERO.matcher(lines[i]).find() || SIZE_GT_ZERO.matcher(lines[i]).find()) {
                addIssue(i + 1, "Use isEmpty() instead of size() == 0 or size() > 0.");
            }
        }
    }
}
