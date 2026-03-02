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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methods must not have identical implementations.
 */
@Rule(
        key = "qa-design-no-identical-methods",
        name = "Methods must not have identical implementations",
        description = "Detects methods with identical implementations that should be consolidated into a single shared method.",
        tags = {"design", "duplicate"},
        priority = Priority.MAJOR
)
public class NoIdenticalMethodsCheck extends BaseCheck {

    private static final Pattern METHOD_SIG = Pattern.compile(
            "(?i)(?:public|private|protected|global)\\s+(?:static\\s+)?\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        List<String> bodies = new ArrayList<>();
        List<Integer> startLines = new ArrayList<>();
        Matcher m = METHOD_SIG.matcher(stripped);
        while (m.find()) {
            int bracePos = m.end() - 1;
            String body = extractBody(stripped, bracePos);
            if (body == null || body.trim().isEmpty()) continue;
            String normalized = body.replaceAll("\\s+", " ").trim();
            bodies.add(normalized);
            startLines.add(ApexContentHelper.lineAtOffset(content, m.start()));
        }
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                if (bodies.get(i).equals(bodies.get(j))) {
                    addIssue(startLines.get(i), "Method has identical implementation to another method.");
                    addIssue(startLines.get(j), "Method has identical implementation to another method.");
                    return;
                }
            }
        }
    }

    private static String extractBody(String content, int openBrace) {
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
