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
 * Prefer properties over explicit get/set methods.
 */
@Rule(
        key = "qa-convention-no-explicit-getset",
        name = "Prefer properties over explicit get/set methods",
        description = "Explicit getter and setter methods add boilerplate that Apex properties eliminate, making the code less idiomatic and harder to maintain",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class NoExplicitGetsetCheck extends BaseCheck {

    private static final Pattern GET_METHOD = Pattern.compile("\\b(?:public|global)\\s+(?:static\\s+)?\\w+\\s+get(\\w+)\\s*\\(");
    private static final Pattern SET_METHOD = Pattern.compile("\\b(?:public|global)\\s+(?:static\\s+)?void\\s+set(\\w+)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (GET_METHOD.matcher(line).find() || SET_METHOD.matcher(line).find()) {
                addIssue(i + 1, "Prefer properties over explicit get/set methods.");
                break;
            }
        }
    }
}
