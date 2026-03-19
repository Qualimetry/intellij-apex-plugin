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
 * Return empty collections instead of null.
 */
@Rule(
        key = "qa-design-return-empty-collection",
        name = "Return empty collections instead of null",
        description = "Returning null instead of an empty collection forces every caller to add null checks, increasing the risk of NullPointerException",
        tags = {"convention", "cert"},
        priority = Priority.MINOR
)
public class ReturnEmptyCollectionCheck extends BaseCheck {

    private static final Pattern RETURN_NULL = Pattern.compile("(?i)return\\s+null\\s*;");
    private static final Pattern COLLECTION_RETURN_TYPE = Pattern.compile(
            "(?i)\\b(?:List\\s*<|Set\\s*<|Map\\s*<|\\w+\\[\\s*])");
    private static final Pattern METHOD_DECL = Pattern.compile(
            "(?i)\\b(?:public|private|protected|global|static|override|virtual|testmethod)\\s+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!RETURN_NULL.matcher(lines[i]).find()) continue;

            for (int j = i - 1; j >= 0; j--) {
                String candidate = lines[j].trim();
                if (candidate.isEmpty() || candidate.equals("{") || candidate.equals("}")) continue;
                if (candidate.startsWith("if") || candidate.startsWith("else") ||
                        candidate.startsWith("for") || candidate.startsWith("while") ||
                        candidate.startsWith("try") || candidate.startsWith("catch")) continue;

                if (METHOD_DECL.matcher(candidate).find() || candidate.matches("(?i).*\\w+\\s+\\w+\\s*\\(.*")) {
                    if (COLLECTION_RETURN_TYPE.matcher(candidate).find()) {
                        addIssue(i + 1, "Return empty collections instead of null.");
                    }
                    break;
                }
                break;
            }
        }
    }
}
