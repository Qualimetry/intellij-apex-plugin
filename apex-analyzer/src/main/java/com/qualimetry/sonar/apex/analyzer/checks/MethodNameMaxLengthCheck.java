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
 * Method names should not exceed maximum length (default 64).
 */
@Rule(
        key = "qa-convention-method-name-max-length",
        name = "Method names should not exceed maximum length",
        description = "Excessively long method names hurt readability and suggest the method is doing too much, warranting a split into smaller methods",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class MethodNameMaxLengthCheck extends BaseCheck {

    private static final int MAX_LENGTH = 64;
    private static final Pattern METHOD_NAME = Pattern.compile("(?:public|private|protected|global)\\s+(?:static\\s+)?(?:override\\s+)?(?:\\w+\\s+)?(\\w+)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = METHOD_NAME.matcher(lines[i]);
            if (m.find() && m.group(1).length() > MAX_LENGTH) {
                addIssue(i + 1, "Method name exceeds maximum length of " + MAX_LENGTH + ".");
            }
        }
    }
}
