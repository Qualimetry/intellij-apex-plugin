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
 * Session ID retrieval requires security review.
 */
@Rule(
        key = "qa-security-session-id-review",
        name = "Session ID retrieval requires security review",
        description = "Exposing the session ID to clients or logs enables session hijacking and user impersonation",
        tags = {"salesforce"},
        priority = Priority.CRITICAL
)
public class SessionIdReviewCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("UserInfo.getSessionId")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("UserInfo.getSessionId()")) {
                addIssue(i + 1, "Review use of UserInfo.getSessionId() for security impact.");
            }
        }
    }
}
