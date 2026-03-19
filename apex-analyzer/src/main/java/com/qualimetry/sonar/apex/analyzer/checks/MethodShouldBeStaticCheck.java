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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methods not using instance state should be static.
 */
@Rule(
        key = "qa-design-method-should-be-static",
        name = "Methods not using instance state should be static",
        description = "A method that never accesses instance fields or this can be declared static, making it easier to test and callable without instantiation",
        tags = {"design"},
        priority = Priority.MINOR
)
public class MethodShouldBeStaticCheck extends BaseCheck {

    private static final Pattern INSTANCE_FIELD = Pattern.compile(
            "(?i)^\\s*(?:private|protected|public)\\s+(?!static\\b)(?:\\w+(?:<[^>]+>)?)\\s+(\\w+)\\s*[;=]");

    private static final Pattern METHOD_DECL = Pattern.compile(
            "(?i)^\\s*(?:(?:public|private|protected|global)\\s+)?" +
            "(?:(?:static|override|virtual|abstract|testmethod)\\s+)*" +
            "(?:\\w+(?:<[^>]+>)?)\\s+(\\w+)\\s*\\(");

    private static final Pattern SKIP_ANNOTATION = Pattern.compile(
            "(?i)^\\s*@\\s*(Override|AuraEnabled|IsTest|TestSetup)\\b");

    private static final Pattern STATIC_KEYWORD = Pattern.compile("(?i)\\bstatic\\b");
    private static final Pattern ABSTRACT_VIRTUAL_OVERRIDE = Pattern.compile("(?i)\\b(abstract|virtual|override)\\b");
    private static final Pattern CLASS_DECL = Pattern.compile("(?i)\\bclass\\s+(\\w+)");
    private static final Pattern PROPERTY_ACCESSOR = Pattern.compile("(?i)\\{\\s*get\\s*[;{]");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        String[] lines = stripped.split("\n");

        String className = extractClassName(stripped);
        List<String> instanceFields = collectInstanceFields(lines);

        for (int i = 0; i < lines.length; i++) {
            Matcher mm = METHOD_DECL.matcher(lines[i]);
            if (!mm.find()) {
                continue;
            }

            if (STATIC_KEYWORD.matcher(lines[i]).find()) {
                continue;
            }
            if (ABSTRACT_VIRTUAL_OVERRIDE.matcher(lines[i]).find()) {
                continue;
            }

            String methodName = mm.group(1);
            if (className != null && methodName.equalsIgnoreCase(className)) {
                continue;
            }

            if (hasSkipAnnotation(lines, i)) {
                continue;
            }

            if (PROPERTY_ACCESSOR.matcher(lines[i]).find()) {
                continue;
            }

            String body = extractMethodBody(lines, i);
            if (body == null) {
                continue;
            }

            if (body.contains("this.")) {
                continue;
            }

            boolean usesInstanceField = false;
            for (String field : instanceFields) {
                if (Pattern.compile("\\b" + Pattern.quote(field) + "\\b").matcher(body).find()) {
                    usesInstanceField = true;
                    break;
                }
            }

            if (!usesInstanceField) {
                addIssue(i + 1, "This method does not use instance state and should be static.");
            }
        }
    }

    private boolean hasSkipAnnotation(String[] lines, int methodLine) {
        for (int j = methodLine - 1; j >= 0 && j >= methodLine - 3; j--) {
            String trimmed = lines[j].trim();
            if (trimmed.isEmpty()) continue;
            if (SKIP_ANNOTATION.matcher(lines[j]).find()) return true;
            if (!trimmed.startsWith("@")) break;
        }
        return false;
    }

    private String extractClassName(String stripped) {
        Matcher m = CLASS_DECL.matcher(stripped);
        return m.find() ? m.group(1) : null;
    }

    private List<String> collectInstanceFields(String[] lines) {
        List<String> fields = new ArrayList<>();
        int braceDepth = 0;
        for (String line : lines) {
            for (char c : line.toCharArray()) {
                if (c == '{') braceDepth++;
                else if (c == '}') braceDepth--;
            }
            if (braceDepth == 1) {
                Matcher fm = INSTANCE_FIELD.matcher(line);
                if (fm.find()) {
                    fields.add(fm.group(1));
                }
            }
        }
        return fields;
    }

    private String extractMethodBody(String[] lines, int startLine) {
        int braceCount = 0;
        boolean foundOpen = false;
        StringBuilder body = new StringBuilder();
        for (int i = startLine; i < lines.length; i++) {
            for (char c : lines[i].toCharArray()) {
                if (c == '{') {
                    braceCount++;
                    foundOpen = true;
                } else if (c == '}') {
                    braceCount--;
                }
            }
            if (foundOpen) {
                body.append(lines[i]).append('\n');
            }
            if (foundOpen && braceCount == 0) {
                return body.toString();
            }
        }
        return null;
    }
}
