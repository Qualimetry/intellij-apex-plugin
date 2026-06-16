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
 * Boolean method parameters reduce API clarity.
 */
@Rule(
        key = "qa-design-boolean-method-param",
        name = "Boolean method parameters reduce API clarity",
        description = "Flags methods that accept Boolean parameters, which make call sites ambiguous; prefer overloads or options types.",
        tags = {"design", "confusing"},
        priority = Priority.MINOR
)
public class BooleanMethodParamCheck extends BaseCheck {

    private static final Pattern BOOLEAN_PARAM = Pattern.compile("\\([^)]*\\bBoolean\\s+\\w+[^)]*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (BOOLEAN_PARAM.matcher(lines[i]).find()) {
                addIssue(i + 1, "Boolean method parameters reduce API clarity; consider splitting or using an options type.");
            }
        }
    }
}
