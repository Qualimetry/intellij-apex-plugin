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
 * Cookie usage requires security review.
 */
@Rule(
        key = "qa-security-cookie-usage-review",
        name = "Cookie usage requires security review",
        description = "Cookies are a common attack vector for session hijacking and XSS; each usage must be reviewed for secure flags and scope",
        tags = {"cwe"},
        priority = Priority.CRITICAL
)
public class CookieUsageReviewCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Cookie") && !content.contains("setCookie")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("new Cookie(") || lines[i].contains("setCookie")) {
                addIssue(i + 1, "Review cookie usage for secure and HttpOnly settings.");
            }
        }
    }
}
