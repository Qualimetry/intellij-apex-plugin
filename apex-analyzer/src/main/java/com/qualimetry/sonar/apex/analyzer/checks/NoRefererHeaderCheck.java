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

/**
 * HTTP Referer must not be used for security decisions.
 */
@Rule(
        key = "qa-security-no-referer-header",
        name = "HTTP Referer must not be used for security decisions",
        description = "The Referer header is client-controlled and easily spoofed, making it unsuitable for authorization or CSRF protection",
        tags = {"cwe"},
        priority = Priority.CRITICAL
)
public class NoRefererHeaderCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("Referer") && (line.contains("getHeader") || line.contains("getHeaders") || line.contains(".get("))) {
                addIssue(i + 1, "Do not use HTTP Referer for security decisions; it can be spoofed.");
            }
        }
    }
}
