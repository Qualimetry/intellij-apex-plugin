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
 * Flags field declarations whose names indicate personally identifiable
 * information so they can be reviewed for access control and encryption.
 */
@Rule(
        key = "qa-security-pii-field-detection",
        name = "PII fields must be flagged",
        description = "Unprotected PII fields create regulatory compliance risks (GDPR, CCPA) and data breach consequences",
        tags = {"cwe"},
        priority = Priority.CRITICAL
)
public class PiiFieldDetectionCheck extends BaseCheck {

    private static final Pattern FIELD_DECL = Pattern.compile(
            "^\\s*(?:(?:public|private|protected|global|static|final|transient)\\s+)+\\w+(?:<[^>]+>)?\\s+(\\w+)\\s*[;=]",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern PII_ALWAYS = Pattern.compile(
            "(?i)(SSN|SocialSecurity|TaxId|CreditCard|NationalId|PassportNumber|DriversLicense|BankAccount)");

    private static final Pattern PII_CUSTOM_FIELD = Pattern.compile(
            "(?i)(Email|Phone|BirthDate|Address|FirstName|LastName)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = FIELD_DECL.matcher(lines[i]);
            if (m.find()) {
                String fieldName = m.group(1);
                boolean isPii = PII_ALWAYS.matcher(fieldName).find();
                if (!isPii && fieldName.endsWith("__c")) {
                    isPii = PII_CUSTOM_FIELD.matcher(fieldName).find();
                }
                if (isPii) {
                    addIssue(i + 1, "Flag PII fields for access control and encryption review.");
                }
            }
        }
    }
}
