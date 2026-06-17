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
 * Email must be sent outside of loop bodies.
 */
@Rule(
        key = "qa-salesforce-no-email-in-loop",
        name = "Email must be sent outside of loop bodies",
        description = "Detects Messaging.sendEmail() calls inside loops; emails should be collected and sent in a single batch.",
        tags = {"salesforce", "governor-limits", "bug"},
        priority = Priority.MAJOR
)
public class NoEmailInLoopCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("sendEmail") && !content.contains("Messaging.")) return;
        String[] lines = prepareLines(content);
        int depth = 0;
        boolean inLoop = false;
        int loopDepth = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("for (") || line.contains("while (")) {
                inLoop = true;
                loopDepth = depth;
            }
            if (inLoop && (line.contains("sendEmail") || line.contains("Messaging."))) {
                addIssue(i + 1, "Send email outside of loop bodies.");
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth <= loopDepth) inLoop = false;
        }
    }

    private static int countChar(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) n++;
        }
        return n;
    }
}
