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
 * HTTP requests should set explicit timeout.
 */
@Rule(
        key = "qa-salesforce-http-timeout",
        name = "HTTP requests should set explicit timeout",
        description = "Detects HTTP send() calls not preceded by setTimeout(), risking full transaction time consumption on slow endpoints.",
        tags = {"salesforce", "reliability", "performance"},
        priority = Priority.MAJOR
)
public class HttpTimeoutCheck extends BaseCheck {

    private static final Pattern SEND_CALL = Pattern.compile("\\.send\\s*\\(");
    private static final Pattern SET_TIMEOUT = Pattern.compile("\\.setTimeout\\s*\\(");

    private static final int LOOKBACK = 15;

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!SEND_CALL.matcher(lines[i]).find()) continue;
            boolean hasTimeout = false;
            int start = Math.max(0, i - LOOKBACK);
            for (int j = start; j < i; j++) {
                if (SET_TIMEOUT.matcher(lines[j]).find()) {
                    hasTimeout = true;
                    break;
                }
            }
            if (!hasTimeout) {
                addIssue(i + 1, "HTTP requests should set explicit timeout.");
            }
        }
    }
}
