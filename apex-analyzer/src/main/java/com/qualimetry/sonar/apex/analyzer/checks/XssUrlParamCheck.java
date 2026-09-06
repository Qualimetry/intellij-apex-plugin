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
 * URL parameters must be escaped to prevent XSS.
 */
@Rule(
        key = "qa-security-xss-url-param",
        name = "URL parameters must be escaped to prevent XSS",
        description = "Detects URL parameters added to PageReference without encoding, creating cross-site scripting (XSS) vulnerabilities.",
        tags = {"security", "cwe", "xss"},
        priority = Priority.CRITICAL
)
public class XssUrlParamCheck extends BaseCheck {

    private static final Pattern PAGE_REFERENCE = Pattern.compile("Page\\.\\w+");
    private static final Pattern PUT_PARAM = Pattern.compile("\\.getParameters\\s*\\(\\)\\s*\\.put\\s*\\(|\\.put\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!PAGE_REFERENCE.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (PUT_PARAM.matcher(line).find()
                    && !line.contains("EncodingUtil.urlEncode") && !line.contains("escape")) {
                addIssue(i + 1, "URL parameters must be escaped to prevent XSS.");
            }
        }
    }
}
