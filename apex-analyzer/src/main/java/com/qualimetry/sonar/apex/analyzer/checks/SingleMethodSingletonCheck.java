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
 * Singleton pattern must not rely on a single entry method.
 */
@Rule(
        key = "qa-complexity-single-method-singleton",
        name = "Singleton must not rely on a single entry method",
        description = "A single-method singleton hides excessive complexity; expose multiple focused public methods instead",
        tags = {"brain-overload"},
        priority = Priority.CRITICAL
)
public class SingleMethodSingletonCheck extends BaseCheck {

    private static final Pattern GET_INSTANCE = Pattern.compile("getInstance\\s*\\(");
    private static final Pattern PUBLIC_STATIC = Pattern.compile("\\bpublic\\s+static\\s+\\w+\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!GET_INSTANCE.matcher(content).find()) return;
        String noComments = content.replaceAll("/\\*[\\s\\S]*?\\*/", " ").replaceAll("//[^\n]*", " ");
        int publicStaticCount = 0;
        var m = PUBLIC_STATIC.matcher(noComments);
        while (m.find()) publicStaticCount++;
        if (publicStaticCount > 1) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().startsWith("class ") || lines[i].contains(" class ")) {
                addIssue(i + 1, "Singleton should not rely on a single public static method; add more entry points.");
                return;
            }
        }
    }
}
