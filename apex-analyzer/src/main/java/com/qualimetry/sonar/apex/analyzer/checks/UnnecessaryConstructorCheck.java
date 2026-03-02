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
 * Constructors that only call super() are redundant.
 */
@Rule(
        key = "qa-design-unnecessary-constructor",
        name = "Constructors that only call super() are redundant",
        description = "A constructor whose only statement is super() duplicates what the compiler generates automatically and should be removed",
        tags = {"suspicious"},
        priority = Priority.MAJOR
)
public class UnnecessaryConstructorCheck extends BaseCheck {

    private static final Pattern CONSTRUCTOR_BODY = Pattern.compile("\\{\\s*super\\s*\\([^)]*\\)\\s*;\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("\\s+", " ");
        if (CONSTRUCTOR_BODY.matcher(normalized).find()) {
            String[] lines = prepareLines(content);
            for (int i = 0; i < lines.length; i++) {
                if (Pattern.compile("\\w+\\s*\\([^)]*\\)\\s*\\{").matcher(lines[i]).find()) {
                    String rest = String.join(" ", java.util.Arrays.copyOfRange(lines, i, Math.min(lines.length, i + 5)));
                    if (CONSTRUCTOR_BODY.matcher(rest.replaceAll("\\s+", " ")).find()) {
                        addIssue(i + 1, "Constructors that only call super() are redundant.");
                        return;
                    }
                }
            }
        }
    }
}
