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
 * Final fields with constant values should be static.
 */
@Rule(
        key = "qa-convention-final-field-should-be-static",
        name = "Final fields with constant values should be static",
        description = "A final instance field with a constant value wastes memory by duplicating the same data in every instance instead of sharing it via static",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class FinalFieldShouldBeStaticCheck extends BaseCheck {

    private static final Pattern FINAL_FIELD = Pattern.compile("(?:public|private|protected)\\s+final\\s+(?!static)\\w+\\s+(\\w+)\\s*=");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (FINAL_FIELD.matcher(lines[i]).find()) {
                addIssue(i + 1, "Final fields with constant values should be static.");
            }
        }
    }
}
