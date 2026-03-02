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
 * Data encryption must be reviewed for correctness.
 */
@Rule(
        key = "qa-security-encryption-review",
        name = "Data encryption must be reviewed for correctness",
        description = "Incorrect encryption (weak algorithms, hardcoded keys, wrong mode) provides a false sense of security while data remains vulnerable",
        tags = {"owasp-a5", "owasp-a2"},
        priority = Priority.CRITICAL
)
public class EncryptionReviewCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Crypto.encrypt") && !content.contains("Crypto.decrypt")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Crypto.encrypt") || lines[i].contains("Crypto.decrypt")) {
                addIssue(i + 1, "Review encryption implementation for algorithm and key management.");
            }
        }
    }
}
