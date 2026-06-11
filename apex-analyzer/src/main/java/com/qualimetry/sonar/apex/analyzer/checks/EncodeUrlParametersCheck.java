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
 * URL parameters must be encoded and sanitized.
 */
@Rule(
        key = "qa-security-encode-url-parameters",
        name = "URL parameters must be encoded and sanitized",
        description = "Unencoded URL parameters can break redirects and enable parameter injection or XSS attacks",
        tags = {"cwe", "owasp-a2"},
        priority = Priority.MAJOR
)
public class EncodeUrlParametersCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("getParameters()") && !content.contains("setRedirectUrl") && !content.contains("PageReference")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if ((line.contains("setRedirectUrl") || line.contains("PageReference")) && line.contains("+") && !line.contains("EncodingUtil.urlEncode") && !line.contains("urlEncode")) {
                addIssue(i + 1, "Encode and sanitize URL parameters.");
            }
        }
    }
}
