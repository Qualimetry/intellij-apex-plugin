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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classes should define at least one explicit constructor.
 */
@Rule(
        key = "qa-design-require-constructor",
        name = "Classes should define at least one explicit constructor",
        description = "Relying on the implicit default constructor hides initialization requirements and makes the class harder to maintain and extend safely",
        tags = {"convention", "pitfall"},
        priority = Priority.MAJOR
)
public class RequireConstructorCheck extends BaseCheck {

    private static final Pattern CLASS_DECL = Pattern.compile("\\bclass\\s+(\\w+)");
    private static final Pattern CONSTRUCTOR = Pattern.compile("\\b\\w+\\s*\\([^)]*\\)\\s*\\{");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher cm = CLASS_DECL.matcher(lines[i]);
            if (cm.find() && !lines[i].contains("interface ")) {
                String className = cm.group(1);
                String after = String.join("\n", java.util.Arrays.asList(lines).subList(i, lines.length));
                if (!Pattern.compile("\\b" + Pattern.quote(className) + "\\s*\\(").matcher(after).find()) {
                    addIssue(i + 1, "Class should define at least one explicit constructor.");
                }
            }
        }
    }
}
