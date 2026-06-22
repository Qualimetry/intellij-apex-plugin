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
 * Class fields must not be public.
 */
@Rule(
        key = "qa-security-no-public-class-fields",
        name = "Class fields must not be public",
        description = "Public fields break encapsulation and allow uncontrolled modification of internal state",
        tags = {"cwe", "convention"},
        priority = Priority.MINOR
)
public class NoPublicClassFieldsCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if ((line.matches(".*\\bpublic\\s+(?:static\\s+)?(?:Integer|String|Boolean|List|Set|Map|Id|Blob|Date|Datetime|Decimal|Long|Double)\\s+\\w+\\s*[=;].*") ||
                    (line.contains("public ") && line.contains(";") && !line.contains("(") && (line.contains("String ") || line.contains("Integer ") || line.contains("Boolean ") || line.contains("List") || line.contains("Id ")))) && !line.trim().startsWith("//")) {
                addIssue(i + 1, "Do not declare class fields with public access.");
            }
        }
    }
}
