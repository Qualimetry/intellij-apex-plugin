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
 * Methods must not share the name of their enclosing class.
 */
@Rule(
        key = "qa-design-method-name-matches-class",
        name = "Methods must not share the name of their enclosing class",
        description = "A method named identically to its enclosing class looks like a constructor but behaves as a regular method, confusing callers",
        tags = {"convention", "naming"},
        priority = Priority.MAJOR
)
public class MethodNameMatchesClassCheck extends BaseCheck {

    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+(\\w+)");
    private static final Pattern METHOD_NAME = Pattern.compile("\\b(?:public|private|protected|global)\\s+(?:static\\s+)?(?:override\\s+)?(?:virtual\\s+)?\\w+\\s+(\\w+)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        String currentClass = null;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            Matcher cn = CLASS_NAME.matcher(lines[i]);
            if (cn.find() && !lines[i].contains("interface ")) {
                currentClass = cn.group(1);
            }
            if (currentClass != null) {
                if (lines[i].contains("{")) braceCount++;
                Matcher mn = METHOD_NAME.matcher(lines[i]);
                if (mn.find() && currentClass.equals(mn.group(1))) {
                    addIssue(i + 1, "Methods must not share the name of their enclosing class.");
                }
                if (lines[i].contains("}")) braceCount--;
                if (braceCount == 0) currentClass = null;
            }
        }
    }
}
