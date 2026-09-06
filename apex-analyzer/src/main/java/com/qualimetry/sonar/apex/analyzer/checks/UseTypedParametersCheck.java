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
 * Use typed parameters instead of generic Object.
 */
@Rule(
        key = "qa-complexity-use-typed-parameters",
        name = "Use typed parameters instead of generic Object",
        description = "Object parameters lose compile-time type safety and force error-prone casting at call sites",
        tags = {"brain-overload"},
        priority = Priority.MINOR
)
public class UseTypedParametersCheck extends BaseCheck {

    private static final Pattern OBJECT_PARAM = Pattern.compile("\\)\\s*\\{", Pattern.DOTALL);
    private static final Pattern OBJECT_PARAM_DECL = Pattern.compile("\\([^)]*\\bObject\\s+\\w+\\s*[,)]");

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Object ")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (OBJECT_PARAM_DECL.matcher(line).find()) {
                addIssue(i + 1, "Use a more specific type instead of Object for method parameters.");
            }
        }
    }
}
