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
 * Excessive Queueable chaining risks stack depth limits.
 */
@Rule(
        key = "qa-salesforce-queueable-chain-depth",
        name = "Excessive Queueable chaining risks stack depth limits",
        description = "Flags classes with more than two System.enqueueJob() calls, indicating risky deep Queueable chaining.",
        tags = {"salesforce", "governor-limits", "async"},
        priority = Priority.MAJOR
)
public class QueueableChainDepthCheck extends BaseCheck {

    private static final Pattern SYSTEM_ENQUEUE_JOB = Pattern.compile("System\\.enqueueJob\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        int count = 0;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("System.enqueueJob(")) {
                count++;
            }
        }
        if (count > 2) {
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("System.enqueueJob(")) {
                    addIssue(i + 1, "Excessive Queueable chaining risks stack depth limits.");
                    return;
                }
            }
        }
    }
}
