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
 * Cookies must have the secure flag enabled.
 */
@Rule(
        key = "qa-security-cookie-secure-flag",
        name = "Cookies must have the secure flag enabled",
        description = "Cookies without the Secure flag can be sent over HTTP, exposing session tokens to network-level attackers",
        tags = {"cwe", "salesforce"},
        priority = Priority.MAJOR
)
public class CookieSecureFlagCheck extends BaseCheck {

    private static final Pattern COOKIE_FALSE_SECURE = Pattern.compile("(?:setSecure|isSecure)\\s*\\(\\s*false\\s*\\)");
    private static final Pattern COOKIE_CTOR_FALSE = Pattern.compile("new\\s+Cookie\\s*\\([^)]*,\\s*false\\s*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (COOKIE_FALSE_SECURE.matcher(line).find() || COOKIE_CTOR_FALSE.matcher(line).find()) {
                addIssue(i + 1, "Enable the secure flag on cookies.");
            }
        }
    }
}
