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

/**
 * Error messages in UI must be escaped to prevent XSS.
 */
@Rule(
        key = "qa-security-xss-error-message",
        name = "Error messages in UI must be escaped to prevent XSS",
        description = "Unescaped user data in error messages enables cross-site scripting that can steal session tokens",
        tags = {"cwe", "owasp-a2"},
        priority = Priority.MAJOR
)
public class XssErrorMessageCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("addError") && !content.contains("addMessage")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if ((line.contains("addError(") || line.contains("addMessage(")) && line.contains(", false)")) {
                addIssue(i + 1, "Escape error messages to prevent XSS (do not disable escaping).");
            }
        }
    }
}
