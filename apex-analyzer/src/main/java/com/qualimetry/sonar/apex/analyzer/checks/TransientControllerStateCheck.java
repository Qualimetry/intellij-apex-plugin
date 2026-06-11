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
 * Controller state variables should be marked transient when possible.
 */
@Rule(
        key = "qa-salesforce-transient-controller-state",
        name = "Controller state variables should be marked transient",
        description = "Non-transient fields inflate view state size, degrading page performance and risking the 170 KB limit",
        tags = {"salesforce", "performance"},
        priority = Priority.MAJOR
)
public class TransientControllerStateCheck extends BaseCheck {

    private static final Pattern EXTENDS_CONTROLLER = Pattern.compile("(?i)extends\\s+\\w*Controller\\b");
    private static final Pattern CONTROLLER_CLASS = Pattern.compile("(?i)class\\s+\\w*Controller\\b");
    private static final Pattern INSTANCE_FIELD = Pattern.compile(
            "(?i)\\b(?:public|private|protected)\\s+(?!transient\\b)(?!static\\b)(?!final\\b)\\w+(?:<[^>]*>)?\\s+\\w+\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        if (!EXTENDS_CONTROLLER.matcher(stripped).find() && !CONTROLLER_CLASS.matcher(stripped).find()) {
            return;
        }
        String[] lines = stripped.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (INSTANCE_FIELD.matcher(line).find()
                    && !line.matches("(?i).*\\btransient\\b.*")
                    && !line.matches("(?i).*\\bstatic\\b.*")
                    && !line.matches("(?i).*\\bfinal\\b.*")) {
                addIssue(i + 1, "Mark controller state variables as transient to reduce view state.");
            }
        }
    }
}
