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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Limit coupling between classes.
 */
@Rule(
        key = "qa-complexity-coupling-between-objects",
        name = "Limit coupling between classes",
        description = "High coupling makes code harder to change and test because changes to any dependency ripple through the class",
        tags = {"brain-overload", "bad-practice"},
        priority = Priority.MAJOR
)
public class CouplingBetweenObjectsCheck extends BaseCheck {

    private static final int MAX_COUPLING = 20;
    private static final Pattern CLASS_NAME = Pattern.compile("(?i)\\bclass\\s+(\\w+)");
    private static final Pattern NEW_TYPE = Pattern.compile("\\bnew\\s+([A-Z]\\w*)\\s*\\(");
    private static final Pattern STATIC_CALL = Pattern.compile("\\b([A-Z]\\w*)\\.");

    private static final Set<String> STANDARD_TYPES = Set.of(
            "String", "Integer", "Long", "Double", "Decimal", "Boolean",
            "Id", "Blob", "Date", "DateTime", "Time", "Object",
            "System", "Math", "JSON", "Test", "Assert", "Limits",
            "UserInfo", "Schema", "Type", "EncodingUtil", "Crypto",
            "URL", "Pattern", "Matcher"
    );

    @Override
    public void scan(InputFile file, String content) {
        String stripped = stripContent(content);

        Matcher cm = CLASS_NAME.matcher(stripped);
        String ownClassName = cm.find() ? cm.group(1) : "";

        Set<String> referencedTypes = new HashSet<>();

        Matcher nm = NEW_TYPE.matcher(stripped);
        while (nm.find()) {
            referencedTypes.add(nm.group(1));
        }

        Matcher sm = STATIC_CALL.matcher(stripped);
        while (sm.find()) {
            referencedTypes.add(sm.group(1));
        }

        referencedTypes.removeAll(STANDARD_TYPES);
        referencedTypes.remove(ownClassName);

        if (referencedTypes.size() > MAX_COUPLING) {
            addIssue(1, "Class has too many dependencies on other types (" + referencedTypes.size() + ", max " + MAX_COUPLING + ").");
        }
    }
}
