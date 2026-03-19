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
 * Public static fields must be declared final.
 */
@Rule(
        key = "qa-convention-public-static-must-be-final",
        name = "Public static fields must be declared final",
        description = "Non-final public static fields can be modified from anywhere, creating unpredictable state changes and concurrency risks",
        tags = {"convention"},
        priority = Priority.MAJOR
)
public class PublicStaticMustBeFinalCheck extends BaseCheck {

    private static final Pattern PUBLIC_STATIC_NON_FINAL = Pattern.compile("public\\s+static\\s+(?!final)\\w+\\s+\\w+\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (PUBLIC_STATIC_NON_FINAL.matcher(lines[i]).find()) {
                addIssue(i + 1, "Public static fields must be declared final.");
            }
        }
    }
}
