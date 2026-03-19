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
 * Avoid wrapper objects for primitive conversions.
 */
@Rule(
        key = "qa-performance-no-wrapper-for-conversion",
        name = "Avoid wrapper objects for primitive conversions",
        description = "Creating wrapper objects solely for type conversion wastes heap memory; use static valueOf() methods instead",
        tags = {"performance", "clumsy"},
        priority = Priority.MAJOR
)
public class NoWrapperForConversionCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("new Integer(") || line.contains("new Long(") || line.contains("new Double(") || line.contains("new Boolean(")) {
                addIssue(i + 1, "Use valueOf() or primitive parsing instead of wrapper constructors.");
            }
        }
    }
}
