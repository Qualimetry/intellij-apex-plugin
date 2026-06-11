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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Server-side HTTP requests must validate target URLs.
 * Flags {@code setEndpoint} calls where the argument is a variable or expression (not a string literal).
 */
@Rule(
        key = "qa-security-ssrf-prevention",
        name = "Server-side HTTP requests must validate target URLs",
        description = "User-controlled endpoints allow attackers to scan internal networks or access metadata services via SSRF",
        tags = {"cwe"},
        priority = Priority.CRITICAL
)
public class SsrfPreventionCheck extends BaseCheck {

    private static final Pattern SET_ENDPOINT = Pattern.compile("setEndpoint\\s*\\(\\s*(\\S)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher m = SET_ENDPOINT.matcher(line);
            if (m.find()) {
                char firstArgChar = m.group(1).charAt(0);
                if (firstArgChar != '\'' && !line.contains("callout:")) {
                    addIssue(i + 1, "Validate target URL against an allowlist to prevent SSRF.");
                }
            }
        }
    }
}
