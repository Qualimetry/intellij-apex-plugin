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
import org.sonar.check.RuleProperty;

/**
 * Class source files must not exceed the maximum line count.
 */
@Rule(
        key = "qa-complexity-class-line-limit",
        name = "Class source files must not exceed the maximum line count",
        description = "Detects class files that exceed the configured maximum line count, suggesting the class should be split.",
        tags = {"complexity", "brain-overload"},
        priority = Priority.MAJOR
)
public class ClassLineLimitCheck extends BaseCheck {

    private static final int DEFAULT_MAX_LINES = 500;

    @RuleProperty(
            key = "maxLines",
            description = "Maximum number of lines allowed in a class source file",
            defaultValue = "" + DEFAULT_MAX_LINES)
    private int maxLines = DEFAULT_MAX_LINES;

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        if (lines.length > maxLines) {
            addIssue(1, "Class file has " + lines.length + " lines (max " + maxLines + ").");
        }
    }
}
