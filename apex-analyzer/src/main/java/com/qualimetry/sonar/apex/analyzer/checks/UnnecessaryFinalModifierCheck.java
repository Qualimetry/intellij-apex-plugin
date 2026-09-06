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
 * The final modifier is unnecessary on methods of final classes.
 */
@Rule(
        key = "qa-design-unnecessary-final-modifier",
        name = "The final modifier is unnecessary on methods of final classes",
        description = "Methods in a final class are implicitly final; adding the modifier explicitly is redundant and adds visual noise",
        tags = {"clumsy"},
        priority = Priority.MAJOR
)
public class UnnecessaryFinalModifierCheck extends BaseCheck {

    private static final Pattern FINAL_CLASS = Pattern.compile("\\bfinal\\s+class\\s+\\w+");
    private static final Pattern FINAL_METHOD = Pattern.compile("\\bfinal\\s+(?:static\\s+)?\\w+\\s+\\w+\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inFinalClass = false;
        int braceCount = 0;
        for (int i = 0; i < lines.length; i++) {
            if (FINAL_CLASS.matcher(lines[i]).find()) inFinalClass = true;
            if (inFinalClass && FINAL_METHOD.matcher(lines[i]).find()) {
                addIssue(i + 1, "The final modifier is unnecessary on methods of final classes.");
            }
            if (inFinalClass) {
                if (lines[i].contains("{")) braceCount++;
                if (lines[i].contains("}")) braceCount--;
                if (braceCount == 0) inFinalClass = false;
            }
        }
    }
}
