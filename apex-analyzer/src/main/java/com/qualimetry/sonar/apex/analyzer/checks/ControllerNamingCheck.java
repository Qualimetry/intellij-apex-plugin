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
 * Controller classes must follow naming convention (e.g. end with Controller).
 */
@Rule(
        key = "qa-convention-controller-naming",
        name = "Controller classes must follow naming convention",
        description = "Without a Controller suffix, developers reviewing page markup cannot quickly identify which Apex class handles the server-side logic",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class ControllerNamingCheck extends BaseCheck {

    private static final Pattern CLASS = Pattern.compile("\\bclass\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("Controller") && !content.contains("PageReference") && !content.contains("AuraEnabled")) {
            return;
        }
        Matcher m = CLASS.matcher(content);
        if (m.find()) {
            String name = m.group(1);
            if ((content.contains("PageReference") || content.contains("AuraEnabled")) && !name.endsWith("Controller")) {
                addIssue(lineOf(content, m.start()), "Controller class '" + name + "' should end with Controller.");
            }
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
