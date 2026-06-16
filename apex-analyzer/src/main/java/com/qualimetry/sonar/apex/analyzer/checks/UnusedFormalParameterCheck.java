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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unused method parameters should be removed.
 */
@Rule(
        key = "qa-unused-formal-parameter",
        name = "Unused method parameters should be removed",
        description = "Detects method parameters that are declared but never referenced in the method body.",
        tags = {"unused"},
        priority = Priority.MAJOR
)
public class UnusedFormalParameterCheck extends BaseCheck {

    private static final Pattern METHOD_DECL = Pattern.compile(
            "\\b\\w+\\s+\\w+\\s*\\(([^)]*)\\)\\s*\\{");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        Matcher m = METHOD_DECL.matcher(stripped);
        while (m.find()) {
            String params = m.group(1);
            if (params.trim().isEmpty()) continue;
            String body = extractMethodBody(stripped, m.end() - 1);
            if (body == null) continue;
            String[] parts = params.split(",");
            for (String p : parts) {
                String trimmed = p.trim();
                if (trimmed.isEmpty()) continue;
                String name = trimmed.replaceFirst("^.*\\s+(\\w+)\\s*$", "$1");
                if (name.isEmpty() || name.equals(trimmed)) continue;
                Pattern usage = Pattern.compile("\\b" + Pattern.quote(name) + "\\b");
                if (!usage.matcher(body).find()) {
                    int line = ApexContentHelper.lineAtOffset(content, m.start());
                    addIssue(line, "Unused parameter '" + name + "' should be removed.");
                    break;
                }
            }
        }
    }

    private static String extractMethodBody(String content, int openBrace) {
        if (openBrace >= content.length() || content.charAt(openBrace) != '{') return null;
        int depth = 0;
        for (int i = openBrace; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '{') depth++;
            else if (c == '}') {
                depth--;
                if (depth == 0) return content.substring(openBrace + 1, i);
            }
        }
        return null;
    }
}
