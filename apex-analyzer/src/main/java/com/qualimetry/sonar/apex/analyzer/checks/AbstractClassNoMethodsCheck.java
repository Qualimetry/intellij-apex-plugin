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
 * Abstract classes must define at least one method.
 */
@Rule(
        key = "qa-design-abstract-class-no-methods",
        name = "Abstract classes must define at least one method",
        description = "An abstract class without any methods provides no behavioral contract for subclasses and should either declare methods or be removed",
        tags = {"convention"},
        priority = Priority.MAJOR
)
public class AbstractClassNoMethodsCheck extends BaseCheck {

    private static final Pattern ABSTRACT_CLASS = Pattern.compile("\\babstract\\s+class\\s+\\w+");
    private static final Pattern METHOD = Pattern.compile("\\b(void|\\w+)\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (!ABSTRACT_CLASS.matcher(lines[i]).find()) continue;
            boolean foundOpenBrace = false;
            int braceDepth = 0;
            boolean hasMethod = false;
            for (int j = i; j < lines.length; j++) {
                String line = lines[j];
                for (int k = 0; k < line.length(); k++) {
                    char ch = line.charAt(k);
                    if (ch == '{') {
                        braceDepth++;
                        foundOpenBrace = true;
                    } else if (ch == '}') {
                        braceDepth--;
                    }
                }
                if (j > i && METHOD.matcher(line).find()) {
                    hasMethod = true;
                }
                if (foundOpenBrace && braceDepth == 0) break;
            }
            if (!hasMethod) {
                addIssue(i + 1, "Abstract class must define at least one method.");
            }
        }
    }
}
