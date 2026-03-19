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
 * NullPointerException should not be caught explicitly.
 */
@Rule(
        key = "qa-error-handling-no-catch-npe",
        name = "NullPointerException should not be caught explicitly",
        description = "Catching NullPointerException masks programming errors that should be fixed with proper null checks instead",
        tags = {"error-handling", "cwe"},
        priority = Priority.MAJOR
)
public class NoCatchNpeCheck extends BaseCheck {

    private static final Pattern CATCH_NPE = Pattern.compile("\\bcatch\\s*\\([^)]*NullPointerException[^)]*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (CATCH_NPE.matcher(lines[i]).find()) {
                addIssue(i + 1, "NullPointerException should not be caught explicitly.");
            }
        }
    }
}
