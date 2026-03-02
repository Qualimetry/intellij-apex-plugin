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
 * Classes and public methods should have ApexDoc comments.
 */
@Rule(
        key = "qa-documentation-apexdoc-required",
        name = "Classes and public methods should have ApexDoc",
        description = "Requires classes and interfaces to have ApexDoc comments for documentation and tooling support.",
        tags = {"documentation", "convention"},
        priority = Priority.MINOR
)
public class ApexdocRequiredCheck extends BaseCheck {

    private static final Pattern APEXDOC_START = Pattern.compile("/\\*\\*");
    private static final Pattern CLASS_DECL = Pattern.compile("\\b(class|interface)\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!CLASS_DECL.matcher(lines[i].trim()).find()) continue;
            boolean hasDoc = false;
            for (int j = Math.max(0, i - 5); j < i; j++) {
                if (APEXDOC_START.matcher(lines[j].trim()).find()) {
                    hasDoc = true;
                    break;
                }
            }
            if (!hasDoc) {
                addIssue(i + 1, "Classes and interfaces should have ApexDoc.");
            }
        }
    }
}
