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
 * Inner classes must not declare static members.
 */
@Rule(
        key = "qa-design-no-static-in-inner-class",
        name = "Inner classes must not declare static members",
        description = "Apex inner classes cannot use static members; declaring them causes compilation errors or unexpected behavior",
        tags = {"design"},
        priority = Priority.MAJOR
)
public class NoStaticInInnerClassCheck extends BaseCheck {

    private static final Pattern STATIC_IN_CLASS = Pattern.compile("\\bstatic\\s+");
    private static final Pattern CLASS_DECL = Pattern.compile("\\bclass\\s+\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        int braceCount = 0;
        int classCount = 0;
        for (int i = 0; i < lines.length; i++) {
            if (CLASS_DECL.matcher(lines[i]).find()) classCount++;
            if (classCount >= 2 && braceCount >= 1) {
                if (STATIC_IN_CLASS.matcher(lines[i]).find()) {
                    addIssue(i + 1, "Inner classes must not declare static members.");
                }
            }
            if (lines[i].contains("{")) braceCount++;
            if (lines[i].contains("}")) braceCount--;
        }
    }
}
