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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Constructors must not invoke overridable methods.
 */
@Rule(
        key = "qa-design-constructor-no-overridable-call",
        name = "Constructors must not invoke overridable methods",
        description = "Calling a virtual method from a constructor risks invoking a subclass override before the subclass is fully initialized, leading to NullPointerException or corrupt state",
        tags = {"cert", "pitfall"},
        priority = Priority.CRITICAL
)
public class ConstructorNoOverridableCallCheck extends BaseCheck {

    private static final Pattern CLASS_NAME = Pattern.compile("(?i)\\bclass\\s+(\\w+)");
    private static final Pattern VIRTUAL_METHOD = Pattern.compile(
            "(?i)\\b(?:virtual|override)\\s+\\w+\\s+(\\w+)\\s*\\(");
    private static final Pattern METHOD_DECL = Pattern.compile(
            "(?i)\\b(\\w+)\\s*\\([^)]*\\)\\s*\\{");

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);
        String[] lines = prepareLines(content);

        Matcher cm = CLASS_NAME.matcher(stripped);
        if (!cm.find()) return;
        String className = cm.group(1);

        Set<String> overridableMethods = new HashSet<>();
        Matcher vm = VIRTUAL_METHOD.matcher(stripped);
        while (vm.find()) {
            overridableMethods.add(vm.group(1));
        }
        if (overridableMethods.isEmpty()) return;

        boolean inConstructor = false;
        int braceCount = 0;
        int constructorLine = -1;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!inConstructor) {
                Matcher md = METHOD_DECL.matcher(line);
                if (md.find() && md.group(1).equalsIgnoreCase(className)) {
                    String before = line.substring(0, md.start()).trim();
                    boolean hasReturnType = before.matches("(?i).*\\b(void|string|integer|long|double|decimal|boolean|id|list|set|map|object|\\w+)\\s*$");
                    if (!hasReturnType || before.isEmpty() || before.matches("(?i)\\s*(public|private|protected|global)\\s*")) {
                        inConstructor = true;
                        constructorLine = i;
                        braceCount = 0;
                    }
                }
            }
            if (inConstructor) {
                braceCount += ApexContentHelper.countChar(line, '{');
                braceCount -= ApexContentHelper.countChar(line, '}');

                if (i != constructorLine || !line.contains("{")) {
                    for (String methodName : overridableMethods) {
                        Pattern callPattern = Pattern.compile("\\b" + Pattern.quote(methodName) + "\\s*\\(");
                        if (callPattern.matcher(line).find()) {
                            addIssue(i + 1, "Constructors must not invoke overridable methods.");
                            break;
                        }
                    }
                } else {
                    String afterBrace = line.substring(line.indexOf('{') + 1);
                    for (String methodName : overridableMethods) {
                        Pattern callPattern = Pattern.compile("\\b" + Pattern.quote(methodName) + "\\s*\\(");
                        if (callPattern.matcher(afterBrace).find()) {
                            addIssue(i + 1, "Constructors must not invoke overridable methods.");
                            break;
                        }
                    }
                }

                if (braceCount <= 0) {
                    inConstructor = false;
                }
            }
        }
    }
}
