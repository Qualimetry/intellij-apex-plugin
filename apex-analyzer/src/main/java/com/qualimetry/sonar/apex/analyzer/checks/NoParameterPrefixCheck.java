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
 * Method parameter names should not use prefixes (e.g. p_, a_, the).
 */
@Rule(
        key = "qa-convention-no-parameter-prefix",
        name = "Method parameter names should not use prefixes",
        description = "Hungarian-style prefixes on parameters add noise without improving clarity in modern IDEs that already distinguish parameters through highlighting",
        tags = {"convention"},
        priority = Priority.MINOR
)
public class NoParameterPrefixCheck extends BaseCheck {

    private static final Pattern PARAM = Pattern.compile("\\(([^)]*)\\)");
    private static final Pattern PREFIXED_PARAM = Pattern.compile("\\b(?:p_|a_|the)([A-Za-z]\\w*)\\s*(?:,|\\s*[,)])");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher pm = PARAM.matcher(lines[i]);
            while (pm.find()) {
                if (PREFIXED_PARAM.matcher(pm.group(1)).find()) {
                    addIssue(i + 1, "Method parameter names should not use prefixes (p_, a_, the).");
                    break;
                }
            }
        }
    }
}
