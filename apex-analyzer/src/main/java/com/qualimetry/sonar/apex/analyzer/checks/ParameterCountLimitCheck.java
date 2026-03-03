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
 * Methods must not accept too many parameters.
 */
@Rule(
        key = "qa-design-parameter-count-limit",
        name = "Methods must not accept too many parameters",
        description = "Detects methods with more parameters than the configured limit, suggesting use of a wrapper object.",
        tags = {"design", "brain-overload"},
        priority = Priority.MAJOR
)
public class ParameterCountLimitCheck extends BaseCheck {

    private static final int MAX_PARAMS = 7;
    private static final Pattern METHOD_PATTERN = Pattern.compile(
            "\\b(?:public|private|global|protected)?\\s+(?:static\\s+)?(?:override\\s+)?(?:final\\s+)?(?:\\w+\\s+)?\\w+\\s*\\s*\\(([^)]*)\\)",
            Pattern.MULTILINE
    );

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        Matcher m = METHOD_PATTERN.matcher(normalized);
        while (m.find()) {
            String params = m.group(1).trim();
            if (params.isEmpty()) continue;
            int count = 1;
            int depth = 0;
            for (int i = 0; i < params.length(); i++) {
                char c = params.charAt(i);
                if (c == '(' || c == '<') depth++;
                else if (c == ')' || c == '>') depth--;
                else if (c == ',' && depth == 0) count++;
            }
            if (count > MAX_PARAMS) {
                int line = lineAtOffset(content, m.start());
                addIssue(line, "Method has " + count + " parameters (max " + MAX_PARAMS + ").");
            }
        }
    }

    private static int lineAtOffset(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
