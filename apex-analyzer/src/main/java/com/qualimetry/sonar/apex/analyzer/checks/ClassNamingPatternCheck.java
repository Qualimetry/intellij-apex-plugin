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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Class names must conform to the naming pattern.
 */
@Rule(
        key = "qa-convention-class-naming-pattern",
        name = "Class names must conform to the naming pattern",
        description = "Detects class names that do not match the configured naming pattern, defaulting to PascalCase convention.",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class ClassNamingPatternCheck extends BaseCheck {

    private static final String DEFAULT_FORMAT = "^[A-Z][a-zA-Z0-9]*$";

    @RuleProperty(
            key = "format",
            description = "Regular expression that class names must match",
            defaultValue = DEFAULT_FORMAT)
    private String format = DEFAULT_FORMAT;

    private Pattern compiledFormat;

    public void setFormat(String format) {
        this.format = format;
        this.compiledFormat = null;
    }

    private Pattern formatPattern() {
        if (compiledFormat == null) {
            try {
                compiledFormat = Pattern.compile(format);
            } catch (PatternSyntaxException e) {
                compiledFormat = Pattern.compile(DEFAULT_FORMAT);
            }
        }
        return compiledFormat;
    }

    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+([A-Za-z0-9_]+)");

    @Override
    public void scan(InputFile file, String content) {
        Pattern pattern = formatPattern();
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        for (int i = 0; i < normLines.length; i++) {
            java.util.regex.Matcher m = CLASS_NAME.matcher(normLines[i]);
            if (m.find()) {
                String name = m.group(1);
                if (!pattern.matcher(name).matches()) {
                    addIssue(i + 1, "Class names should use PascalCase.");
                }
            }
        }
    }
}
