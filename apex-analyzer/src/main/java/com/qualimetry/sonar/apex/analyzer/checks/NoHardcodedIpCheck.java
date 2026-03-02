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

import java.util.regex.Pattern;

/**
 * IP addresses must not be hardcoded.
 */
@Rule(
        key = "qa-security-no-hardcoded-ip",
        name = "IP addresses must not be hardcoded",
        description = "Detects hardcoded IP addresses in source code, which create security and portability concerns.",
        tags = {"security", "cwe"},
        priority = Priority.MAJOR
)
public class NoHardcodedIpCheck extends BaseCheck {

    private static final Pattern IP_LITERAL = Pattern.compile("\"(?:\\d{1,3}\\.){3}\\d{1,3}\"|'((?:\\d{1,3}\\.){3}\\d{1,3})'");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (IP_LITERAL.matcher(lines[i]).find()) {
                addIssue(i + 1, "Do not hardcode IP addresses; use Named Credentials or custom settings.");
            }
        }
    }
}
