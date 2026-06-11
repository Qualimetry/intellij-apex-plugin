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
 * Externally provided arrays must be defensively copied.
 */
@Rule(
        key = "qa-security-defensive-array-copy",
        name = "Externally provided arrays must be defensively copied",
        description = "Storing a caller-provided collection directly allows external code to modify internal state, breaking encapsulation",
        tags = {"cwe", "unpredictable"},
        priority = Priority.MAJOR
)
public class DefensiveArrayCopyCheck extends BaseCheck {

    private static final Pattern DIRECT_ARRAY_STORE = Pattern.compile("this\\.\\w+\\s*=\\s*\\w+\\s*;");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (DIRECT_ARRAY_STORE.matcher(line).find() && !line.contains(".clone()") && !line.contains("new List") && !line.contains("new Set")) {
                addIssue(i + 1, "Defensively copy externally provided arrays before storing.");
            }
        }
    }
}
