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
 * Cryptographic operations must use random IV/key.
 */
@Rule(
        key = "qa-security-random-iv-key",
        name = "Cryptographic operations must use random IV/key",
        description = "Fixed or predictable IVs and keys allow attackers to decrypt data; always generate them randomly with Crypto.generateAesKey()",
        tags = {"cwe", "owasp-a5"},
        priority = Priority.CRITICAL
)
public class RandomIvKeyCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        boolean hasCryptoEncrypt = content.contains("Crypto.encrypt");
        if (!hasCryptoEncrypt) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if ((line.contains("Blob.valueOf") || line.contains("EncodingUtil.base64Decode")) && (line.contains("key") || line.contains("iv") || line.contains("IV"))) {
                addIssue(i + 1, "Use Crypto.generateAesKey() or a secure random source for IV/key.");
            }
        }
    }
}
