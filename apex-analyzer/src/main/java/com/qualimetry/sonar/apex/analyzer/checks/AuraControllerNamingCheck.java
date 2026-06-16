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
 * Lightning Aura controller name must follow convention (e.g. end with Controller).
 */
@Rule(
        key = "qa-convention-aura-controller-naming",
        name = "Lightning Aura controller name must follow convention",
        description = "Without a Controller suffix, developers cannot quickly identify which classes serve as server-side Aura controllers",
        tags = {"convention", "naming"},
        priority = Priority.MINOR
)
public class AuraControllerNamingCheck extends BaseCheck {

    private static final Pattern CLASS_DECL = Pattern.compile("\\bclass\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("AuraEnabled") && !content.contains("aura:controller")) {
            return;
        }
        var m = CLASS_DECL.matcher(content);
        while (m.find()) {
            String name = m.group(1);
            if (content.contains("AuraEnabled") && !name.endsWith("Controller")) {
                addIssue(lineOf(content, m.start()), "Aura controller class '" + name + "' should end with Controller.");
                break;
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
