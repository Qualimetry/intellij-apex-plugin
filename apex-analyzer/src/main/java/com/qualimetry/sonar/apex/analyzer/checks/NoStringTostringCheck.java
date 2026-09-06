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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Pattern;

/**
 * Calling toString() on a String is redundant.
 */
@Rule(
        key = "qa-design-no-string-tostring",
        name = "Calling toString() on a String is redundant",
        description = "Calling toString() on a value that is already a String adds no value and clutters the code",
        tags = {"clumsy"},
        priority = Priority.MAJOR
)
public class NoStringTostringCheck extends BaseCheck {

    private static final Pattern STRING_TOSTRING = Pattern.compile("\\.toString\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = ApexContentHelper.splitLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (STRING_TOSTRING.matcher(lines[i]).find() && (lines[i].contains("\"") || lines[i].contains("'"))) {
                addIssue(i + 1, "Calling toString() on a String is redundant.");
            }
        }
    }
}
