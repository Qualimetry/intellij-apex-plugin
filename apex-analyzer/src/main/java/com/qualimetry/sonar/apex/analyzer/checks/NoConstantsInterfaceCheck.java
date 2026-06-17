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
 * Interfaces must not be used solely for constants.
 */
@Rule(
        key = "qa-convention-no-constants-interface",
        name = "Interfaces must not be used solely for constants",
        description = "Implementing a constants-only interface pollutes the class API with inherited values and creates unnecessary coupling",
        tags = {"bad-practice"},
        priority = Priority.MINOR
)
public class NoConstantsInterfaceCheck extends BaseCheck {

    private static final Pattern INTERFACE = Pattern.compile("\\binterface\\s+(\\w+)");
    private static final Pattern METHOD_IN_INTERFACE = Pattern.compile("\\b(?:void|\\w+)\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        Matcher ifaceMatcher = INTERFACE.matcher(content);
        if (!ifaceMatcher.find()) return;
        int braceEnd = content.indexOf('}', ifaceMatcher.start());
        if (braceEnd < 0) return;
        String between = content.substring(ifaceMatcher.end(), braceEnd);
        boolean hasMethod = METHOD_IN_INTERFACE.matcher(between).find();
        boolean hasConstant = between.contains("=");
        if (!hasMethod && hasConstant) {
            addIssue(lineOf(content, ifaceMatcher.start()), "Interfaces must not be used solely for constants; use a class instead.");
        }
    }

    private static int lineOf(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
