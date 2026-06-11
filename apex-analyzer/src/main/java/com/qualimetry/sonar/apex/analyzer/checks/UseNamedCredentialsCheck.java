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
 * HTTP callouts must use Named Credentials.
 * Flags {@code setEndpoint} calls with hardcoded URL string literals instead of {@code callout:} references.
 */
@Rule(
        key = "qa-security-use-named-credentials",
        name = "HTTP callouts must use Named Credentials",
        description = "Hardcoded URLs and credentials in callouts expose secrets in source code and prevent per-environment configuration",
        tags = {"cwe"},
        priority = Priority.CRITICAL
)
public class UseNamedCredentialsCheck extends BaseCheck {

    private static final Pattern SET_ENDPOINT_STRING = Pattern.compile("setEndpoint\\s*\\(\\s*'([^']*)'");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher m = SET_ENDPOINT_STRING.matcher(line);
            if (m.find()) {
                String literal = m.group(1);
                if (!literal.contains("callout:")
                        && (literal.contains("http://") || literal.contains("https://"))) {
                    addIssue(i + 1, "Use Named Credentials (callout:Name) for HTTP endpoints.");
                }
            }
        }
    }
}
