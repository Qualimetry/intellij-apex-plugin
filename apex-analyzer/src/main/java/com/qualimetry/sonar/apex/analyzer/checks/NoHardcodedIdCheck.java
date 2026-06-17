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
 * Salesforce record IDs must not be hardcoded.
 */
@Rule(
        key = "qa-salesforce-no-hardcoded-id",
        name = "Salesforce record IDs must not be hardcoded",
        description = "Detects hardcoded Salesforce record IDs (15 or 18 character), which are environment-specific and break across orgs.",
        tags = {"salesforce", "convention"},
        priority = Priority.MAJOR
)
public class NoHardcodedIdCheck extends BaseCheck {

    private static final Pattern ID_18 = Pattern.compile("'[0-9a-zA-Z]{18}'");
    private static final Pattern ID_15 = Pattern.compile("'[0-9a-zA-Z]{15}'");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            if (ID_18.matcher(line).find() || ID_15.matcher(line).find()) {
                addIssue(i + 1, "Do not hardcode Salesforce record IDs; use custom settings or queries.");
            }
        }
    }
}
