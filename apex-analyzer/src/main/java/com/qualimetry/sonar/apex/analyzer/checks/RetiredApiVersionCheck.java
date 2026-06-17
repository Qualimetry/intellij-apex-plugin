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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Apex classes must not reference retired API versions.
 */
@Rule(
        key = "qa-salesforce-retired-api-version",
        name = "Apex classes must not reference retired API versions",
        description = "Retired API versions cause compilation failures and receive no security patches",
        tags = {"retired"},
        priority = Priority.MAJOR
)
public class RetiredApiVersionCheck extends BaseCheck {

    private static final int RETIRED_THRESHOLD = 21;
    private static final Pattern API_VERSION = Pattern.compile("(?:apiVersion|version)\\s*[=:]\\s*(\\d{2,3})", Pattern.CASE_INSENSITIVE);

    @Override
    public void scan(InputFile file, String content) {
        Matcher m = API_VERSION.matcher(content);
        while (m.find()) {
            int version = Integer.parseInt(m.group(1));
            if (version <= RETIRED_THRESHOLD) {
                int line = lineOf(content, m.start());
                addIssue(line, "Do not use retired API version " + version + ". Use a supported version.");
                return;
            }
        }
    }

    private static int lineOf(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
