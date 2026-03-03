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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Declarations must not shadow outer fields or variables.
 */
@Rule(
        key = "qa-convention-no-shadowed-declaration",
        name = "Declarations must not shadow outer fields or variables",
        description = "Shadowed declarations make it unclear whether a reference targets the field or the local variable, leading to accidental value overwrites",
        tags = {"naming", "bad-practice"},
        priority = Priority.MINOR
)
public class NoShadowedDeclarationCheck extends BaseCheck {

    private static final Pattern FIELD = Pattern.compile("(?:private|public|protected|global)\\s+(?:static\\s+)?(?:final\\s+)?(?:transient\\s+)?\\w+\\s+(\\w+)\\s*[;=]");
    private static final Pattern LOCAL_OR_PARAM = Pattern.compile("\\b(?:Integer|String|Boolean|Object|List|Set|Map|\\w+)\\s+(\\w+)\\s*[;,)=]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        Set<String> fieldNames = new HashSet<>();
        for (String line : lines) {
            Matcher fm = FIELD.matcher(line);
            if (fm.find() && !line.trim().startsWith("//")) {
                fieldNames.add(fm.group(1));
            }
        }
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            Matcher fm = FIELD.matcher(line);
            if (fm.find()) continue;
            Matcher lm = LOCAL_OR_PARAM.matcher(line);
            while (lm.find()) {
                String name = lm.group(1);
                if (fieldNames.contains(name)) {
                    addIssue(i + 1, "Variable '" + name + "' shadows an outer field or variable.");
                    break;
                }
            }
        }
    }
}
