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
 * AuraEnabled methods must implement structured error handling.
 */
@Rule(
        key = "qa-error-handling-auraenabled-error-handling",
        name = "AuraEnabled methods must implement structured error handling",
        description = "Without try-catch in @AuraEnabled methods, unhandled exceptions surface as opaque errors in Lightning components",
        tags = {"salesforce", "convention"},
        priority = Priority.MAJOR
)
public class AuraenabledErrorHandlingCheck extends BaseCheck {

    private static final Pattern AURA_ENABLED = Pattern.compile("@AuraEnabled\\b");
    private static final Pattern TRY_CATCH = Pattern.compile("\\btry\\s*\\{");
    private static final Pattern CATCH = Pattern.compile("\\bcatch\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replace("\r\n", "\n").replace('\r', '\n');
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (AURA_ENABLED.matcher(lines[i]).find()) {
                int lineStart = 0;
                for (int k = 0; k < i; k++) lineStart += lines[k].length() + 1;
                String fromMethod = normalized.substring(lineStart);
                int braceCount = 0;
                int end = 0;
                for (int j = 0; j < fromMethod.length(); j++) {
                    char c = fromMethod.charAt(j);
                    if (c == '{') braceCount++;
                    else if (c == '}') {
                        braceCount--;
                        if (braceCount == 0) { end = j; break; }
                    }
                }
                String methodBody = end > 0 ? fromMethod.substring(0, end + 1) : fromMethod;
                if (!TRY_CATCH.matcher(methodBody).find() || !CATCH.matcher(methodBody).find()) {
                    addIssue(i + 1, "AuraEnabled methods must implement structured error handling.");
                }
            }
        }
    }
}
