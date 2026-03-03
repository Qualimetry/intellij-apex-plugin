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
 * Empty methods in abstract classes should be declared abstract.
 */
@Rule(
        key = "qa-design-empty-method-should-be-abstract",
        name = "Empty methods in abstract classes should be declared abstract",
        description = "An empty method body in an abstract class suggests the method is meant to be overridden; declaring it abstract makes this intent explicit",
        tags = {"design", "unused"},
        priority = Priority.MINOR
)
public class EmptyMethodShouldBeAbstractCheck extends BaseCheck {

    private static final Pattern ABSTRACT_CLASS = Pattern.compile("\\babstract\\s+class\\s+\\w+");
    private static final Pattern EMPTY_METHOD = Pattern.compile("(?:public|private|protected|global)\\s+(?:virtual\\s+)?\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{\\s*\\}");

    @Override
    public void scan(InputFile file, String content) {
        String normalized = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        if (!ABSTRACT_CLASS.matcher(normalized).find()) return;
        String[] lines = prepareLines(content);
        boolean inAbstractClass = false;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            if (ABSTRACT_CLASS.matcher(lines[i]).find()) inAbstractClass = true;
            if (inAbstractClass && lines[i].matches(".*\\{\\s*\\}\\s*")) {
                addIssue(i + 1, "Empty methods in abstract classes should be declared abstract.");
            }
            if (inAbstractClass) {
                if (lines[i].contains("{")) braceCount++;
                if (lines[i].contains("}")) braceCount--;
                if (braceCount == 0) inAbstractClass = false;
            }
        }
    }
}
