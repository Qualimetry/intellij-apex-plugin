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
 * Private fields used only in one method should be local variables.
 */
@Rule(
        key = "qa-design-private-field-should-be-local",
        name = "Private fields used only in one method should be local variables",
        description = "A private field accessed in only one method unnecessarily broadens its scope and lifetime, making the class harder to reason about",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class PrivateFieldShouldBeLocalCheck extends BaseCheck {

    private static final Pattern PRIVATE_FIELD = Pattern.compile(
            "(?i)\\bprivate\\s+(?:static\\s+)?(?:final\\s+)?(?:\\w+(?:<[^>]+>)?)\\s+(\\w+)\\s*[;=]");

    private static final Pattern METHOD_DECL = Pattern.compile(
            "(?i)^\\s*(?:(?:public|private|protected|global)\\s+)?" +
            "(?:(?:static|override|virtual|abstract|testmethod)\\s+)*" +
            "(?:\\w+(?:<[^>]+>)?)\\s+(\\w+)\\s*\\(");

    private static final Pattern CLASS_DECL = Pattern.compile("(?i)\\bclass\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        String[] lines = stripped.split("\n");

        List<Integer> fieldLines = new ArrayList<>();
        List<String> fieldNames = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            Matcher fm = PRIVATE_FIELD.matcher(lines[i]);
            if (fm.find() && !CLASS_DECL.matcher(lines[i]).find()) {
                fieldLines.add(i);
                fieldNames.add(fm.group(1));
            }
        }

        List<int[]> methodBodies = extractMethodBodies(lines);

        for (int f = 0; f < fieldNames.size(); f++) {
            String fieldName = fieldNames.get(f);
            int fieldLine = fieldLines.get(f);
            Pattern fieldRef = Pattern.compile("\\b" + Pattern.quote(fieldName) + "\\b");

            int methodsUsingField = 0;
            for (int[] body : methodBodies) {
                int bodyStart = body[0];
                int bodyEnd = body[1];
                for (int i = bodyStart; i <= bodyEnd; i++) {
                    if (fieldRef.matcher(lines[i]).find()) {
                        methodsUsingField++;
                        break;
                    }
                }
            }

            if (methodsUsingField == 1) {
                addIssue(fieldLine + 1,
                        "Private field '" + fieldName + "' is used in only one method; make it a local variable.");
            }
        }
    }

    private List<int[]> extractMethodBodies(String[] lines) {
        List<int[]> bodies = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!METHOD_DECL.matcher(lines[i]).find()) {
                continue;
            }
            if (CLASS_DECL.matcher(lines[i]).find()) {
                continue;
            }
            int braceCount = 0;
            boolean foundOpen = false;
            for (int j = i; j < lines.length; j++) {
                for (char c : lines[j].toCharArray()) {
                    if (c == '{') {
                        braceCount++;
                        foundOpen = true;
                    } else if (c == '}') {
                        braceCount--;
                    }
                }
                if (foundOpen && braceCount == 0) {
                    bodies.add(new int[]{i, j});
                    i = j;
                    break;
                }
            }
        }
        return bodies;
    }
}
