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
 * Constant field names must use UPPER_SNAKE_CASE.
 */
@Rule(
        key = "qa-convention-constant-naming",
        name = "Constant field names must use UPPER_SNAKE_CASE",
        description = "Detects constant (static final) field names that do not follow UPPER_SNAKE_CASE convention.",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class ConstantNamingCheck extends BaseCheck {

    private static final Pattern STATIC_FINAL_FIELD = Pattern.compile(
            "\\b(?:public|private|protected)\\s+static\\s+final\\s+(?:\\w+)\\s+([A-Za-z][A-Za-z0-9_]*)\\s*[=;]"
    );
    private static final Pattern UPPER_SNAKE = Pattern.compile("^[A-Z][A-Z0-9_]*(_[A-Z0-9_]+)*[A-Z0-9_]*$");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = normalized.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Matcher m = STATIC_FINAL_FIELD.matcher(lines[i]);
            while (m.find()) {
                String name = m.group(1);
                if (!UPPER_SNAKE.matcher(name).matches()) {
                    addIssue(i + 1, "Constant '" + name + "' should use UPPER_SNAKE_CASE.");
                }
            }
        }
    }
}
