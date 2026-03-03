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
 * String case conversions must specify a locale.
 */
@Rule(
        key = "qa-design-locale-in-case-conversion",
        name = "String case conversions must specify a locale",
        description = "Omitting the locale in toUpperCase() or toLowerCase() can produce incorrect results in Turkish and other locale-sensitive contexts",
        tags = {"design", "unpredictable"},
        priority = Priority.MINOR
)
public class LocaleInCaseConversionCheck extends BaseCheck {

    private static final Pattern TOLOWER_NO_LOCALE = Pattern.compile("\\.toLowerCase\\s*\\(\\s*\\)");
    private static final Pattern TOUPPER_NO_LOCALE = Pattern.compile("\\.toUpperCase\\s*\\(\\s*\\)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if ((TOLOWER_NO_LOCALE.matcher(lines[i]).find() || TOUPPER_NO_LOCALE.matcher(lines[i]).find())
                    && !lines[i].contains("Locale.") && !lines[i].trim().startsWith("//")) {
                addIssue(i + 1, "String case conversions must specify a locale.");
            }
        }
    }
}
