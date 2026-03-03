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
 * Trigger names should not exceed maximum length (default 40).
 */
@Rule(
        key = "qa-convention-trigger-name-max-length",
        name = "Trigger names should not exceed maximum length",
        description = "Excessively long trigger names are difficult to reference in logs and documentation, hindering troubleshooting",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class TriggerNameMaxLengthCheck extends BaseCheck {

    private static final int MAX_LENGTH = 40;
    private static final Pattern TRIGGER_NAME = Pattern.compile("trigger\\s+(\\w+)\\s+on\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        Matcher m = TRIGGER_NAME.matcher(content);
        while (m.find()) {
            if (m.group(1).length() > MAX_LENGTH) {
                addIssue(lineOf(content, m.start()), "Trigger name exceeds maximum length of " + MAX_LENGTH + ".");
            }
        }
    }

    private static int lineOf(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
