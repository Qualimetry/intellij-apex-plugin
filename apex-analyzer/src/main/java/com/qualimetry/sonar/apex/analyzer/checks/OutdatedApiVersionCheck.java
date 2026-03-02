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
 * Flags Apex files that reference an outdated Salesforce API version in
 * header comments or embedded metadata XML.
 */
@Rule(
        key = "qa-salesforce-outdated-api-version",
        name = "Apex classes should use a recent Salesforce API version",
        description = "Outdated API versions miss platform improvements and risk deployment failures when Salesforce retires them",
        tags = {"salesforce"},
        priority = Priority.MINOR
)
public class OutdatedApiVersionCheck extends BaseCheck {

    private static final int MIN_RECENT_VERSION = 55;

    // Matches header comments (// API Version: 42.0) and XML (<apiVersion>42.0</apiVersion>)
    private static final Pattern API_VERSION = Pattern.compile(
            "(?i)(?:apiVersion|api\\s*version)\\s*[=:>]\\s*(\\d+)(?:\\.\\d+)?");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = API_VERSION.matcher(lines[i]);
            if (m.find()) {
                int version = Integer.parseInt(m.group(1));
                if (version < MIN_RECENT_VERSION) {
                    addIssue(i + 1, "Use a recent Salesforce API version (e.g. " + MIN_RECENT_VERSION + "+). Current: " + version);
                }
                return;
            }
        }
    }
}
