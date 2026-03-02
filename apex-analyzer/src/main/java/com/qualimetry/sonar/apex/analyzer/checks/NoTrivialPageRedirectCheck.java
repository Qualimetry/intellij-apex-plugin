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
 * Page actions should not perform trivial redirects.
 */
@Rule(
        key = "qa-design-no-trivial-page-redirect",
        name = "Page actions should not perform trivial redirects",
        description = "A page action that simply returns a PageReference with setRedirect adds an unnecessary server round-trip and should be handled declaratively",
        tags = {"optimization", "design"},
        priority = Priority.MINOR
)
public class NoTrivialPageRedirectCheck extends BaseCheck {

    private static final Pattern SET_REDIRECT = Pattern.compile("setRedirect|setRedirectUrl");
    private static final Pattern RETURN_PAGE_REF = Pattern.compile("return\\s+Page\\.\\w+");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (SET_REDIRECT.matcher(lines[i]).find() && RETURN_PAGE_REF.matcher(lines[i]).find()) {
                addIssue(i + 1, "Page actions should not perform trivial redirects.");
            }
        }
    }
}
