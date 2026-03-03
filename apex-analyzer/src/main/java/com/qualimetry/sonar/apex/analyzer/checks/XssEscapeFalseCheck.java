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
 * addError with escape disabled is vulnerable to XSS.
 */
@Rule(
        key = "qa-security-xss-escape-false",
        name = "addError with escape disabled is vulnerable to XSS",
        description = "Flags addError() calls with escape=false that render user content as raw HTML, enabling cross-site scripting.",
        tags = {"security", "cwe", "xss"},
        priority = Priority.CRITICAL
)
public class XssEscapeFalseCheck extends BaseCheck {

    private static final Pattern ADD_ERROR_FALSE = Pattern.compile("addError\\s*\\([^,)]+,\\s*false\\s*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (ADD_ERROR_FALSE.matcher(lines[i]).find()) {
                addIssue(i + 1, "addError with escape=false is vulnerable to XSS; use default escaping or escape manually.");
            }
        }
    }
}
