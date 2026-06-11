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
 * Local variables should not use the final modifier (style preference).
 */
@Rule(
        key = "qa-convention-no-final-local-variable",
        name = "Local variables should not use the final modifier",
        description = "The final modifier on local variables adds visual noise without providing runtime benefits in Apex, reducing readability",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class NoFinalLocalVariableCheck extends BaseCheck {

    private static final Pattern FINAL_LOCAL = Pattern.compile("\\bfinal\\s+(?:Integer|String|Boolean|Object|List|Set|Map|\\w+)\\s+\\w+\\s*[;=]");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().startsWith("//")) continue;
            if (FINAL_LOCAL.matcher(line).find() && !line.contains(" catch ")) {
                addIssue(i + 1, "Local variables should not use the final modifier.");
            }
        }
    }
}
