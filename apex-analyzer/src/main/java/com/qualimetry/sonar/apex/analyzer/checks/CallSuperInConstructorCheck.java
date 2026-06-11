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
 * Constructors should invoke the parent constructor (super() or this()).
 */
@Rule(
        key = "qa-convention-call-super-in-constructor",
        name = "Constructors should invoke the parent constructor",
        description = "Omitting super() in subclass constructors relies on implicit initialization that may not exist, hiding the intended parent setup",
        tags = {"convention", "bad-practice"},
        priority = Priority.MINOR
)
public class CallSuperInConstructorCheck extends BaseCheck {

    private static final Pattern EXTENDS = Pattern.compile("class\\s+\\w+\\s+extends\\s+\\w+");
    private static final Pattern CONSTRUCTOR = Pattern.compile("\\s+(\\w+)\\s*\\(");
    private static final Pattern SUPER_OR_THIS = Pattern.compile("(?:super|this)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!EXTENDS.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        boolean inConstructor = false;
        boolean seenSuperOrThis = false;
        int constructorLine = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher c = CONSTRUCTOR.matcher(line);
            if (c.find() && !line.contains(" void ") && !line.contains(" Boolean ") && !line.contains(" String ")) {
                inConstructor = true;
                seenSuperOrThis = false;
                constructorLine = i + 1;
            }
            if (inConstructor) {
                if (SUPER_OR_THIS.matcher(line).find()) seenSuperOrThis = true;
                if (line.contains("}")) {
                    if (!seenSuperOrThis && constructorLine > 0) {
                        addIssue(constructorLine, "Constructors should invoke the parent constructor (super() or this()).");
                    }
                    inConstructor = false;
                }
            }
        }
    }
}
