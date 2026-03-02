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
 * Constructor method calls must follow field initialization.
 */
@Rule(
        key = "qa-design-constructor-method-call-order",
        name = "Constructor method calls must follow field initialization",
        description = "Calling methods before fields are initialized in a constructor can cause the method to operate on default or null values, leading to subtle bugs",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class ConstructorMethodCallOrderCheck extends BaseCheck {

    private static final Pattern CONSTRUCTOR_START = Pattern.compile("\\s*\\w+\\s*\\([^)]*\\)\\s*\\{");
    private static final Pattern METHOD_CALL = Pattern.compile("\\b(\\w+)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (CONSTRUCTOR_START.matcher(lines[i]).find() && !lines[i].trim().startsWith("//")) {
                int braceCount = 0;
                boolean seenOtherCall = false;
                for (int j = i; j < lines.length; j++) {
                    String line = lines[j];
                    if (line.contains("{")) braceCount++;
                    if (line.contains("}")) braceCount--;
                    if (braceCount == 0 && j > i) break;
                    if (braceCount == 1 && j > i) {
                        Matcher m = METHOD_CALL.matcher(line);
                        while (m.find()) {
                            String name = m.group(1);
                            if (!"super".equals(name) && !"this".equals(name)) {
                                seenOtherCall = true;
                                break;
                            }
                        }
                        if (seenOtherCall) {
                            addIssue(j + 1, "Constructor method calls must follow field initialization.");
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
}
