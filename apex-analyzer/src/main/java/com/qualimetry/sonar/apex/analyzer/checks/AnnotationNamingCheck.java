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
 * Annotations should use consistent casing (e.g. @AuraEnabled not @auraenabled).
 */
@Rule(
        key = "qa-convention-annotation-naming",
        name = "Annotations should use consistent casing",
        description = "Flags annotations that do not start with an uppercase letter, violating standard Apex PascalCase convention.",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class AnnotationNamingCheck extends BaseCheck {

    private static final Pattern AT_ANNOTATION = Pattern.compile("@([a-zA-Z][a-zA-Z0-9]*)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = AT_ANNOTATION.matcher(lines[i]);
            while (m.find()) {
                String ann = m.group(1);
                if (ann.length() > 1 && !Character.isUpperCase(ann.charAt(0))) {
                    addIssue(i + 1, "Annotations should use consistent casing: @" + ann + ".");
                    break;
                }
            }
        }
    }
}
