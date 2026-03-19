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
 * Abstract classes must declare at least one abstract or virtual member.
 */
@Rule(
        key = "qa-design-abstract-needs-abstract-method",
        name = "Abstract classes must declare at least one abstract or virtual member",
        description = "Detects abstract classes that contain no abstract or virtual methods, which cannot be meaningfully extended by subclasses.",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class AbstractNeedsAbstractMethodCheck extends BaseCheck {

    private static final Pattern ABSTRACT_CLASS = Pattern.compile("\\babstract\\s+class\\s+\\w+");
    private static final Pattern ABSTRACT_OR_VIRTUAL = Pattern.compile("\\b(abstract|virtual)\\s+(void|\\w+)\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        String[] lines = prepareLines(content);
        String[] normLines = normalized.split("\n");
        for (int i = 0; i < normLines.length; i++) {
            if (ABSTRACT_CLASS.matcher(normLines[i]).find()) {
                StringBuilder rest = new StringBuilder();
                for (int j = i; j < normLines.length; j++) {
                    rest.append(normLines[j]).append("\n");
                }
                if (!ABSTRACT_OR_VIRTUAL.matcher(rest).find()) {
                    addIssue(i + 1, "Abstract class must declare at least one abstract or virtual member.");
                }
            }
        }
    }
}
