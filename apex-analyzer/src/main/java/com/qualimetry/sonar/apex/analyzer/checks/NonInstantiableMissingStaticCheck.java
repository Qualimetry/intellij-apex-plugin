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
 * Non-instantiable classes must provide static methods.
 */
@Rule(
        key = "qa-design-non-instantiable-missing-static",
        name = "Non-instantiable classes must provide static methods",
        description = "A class with only private constructors but no static methods is inaccessible and serves no purpose",
        tags = {"design", "unused"},
        priority = Priority.MAJOR
)
public class NonInstantiableMissingStaticCheck extends BaseCheck {

    private static final Pattern PRIVATE_CONSTRUCTOR = Pattern.compile("\\bprivate\\s+\\w+\\s*\\([^)]*\\)\\s*\\{");
    private static final Pattern STATIC_METHOD = Pattern.compile("\\bstatic\\s+\\w+\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ");
        if (PRIVATE_CONSTRUCTOR.matcher(normalized).find() && !STATIC_METHOD.matcher(normalized).find()) {
            String[] lines = prepareLines(content);
            for (int i = 0; i < lines.length; i++) {
                if (PRIVATE_CONSTRUCTOR.matcher(lines[i]).find()) {
                    addIssue(i + 1, "Non-instantiable classes must provide static methods.");
                    return;
                }
            }
        }
    }
}
