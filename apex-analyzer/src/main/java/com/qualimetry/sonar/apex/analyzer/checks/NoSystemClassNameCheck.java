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

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom class names must not match Salesforce system class names.
 */
@Rule(
        key = "qa-convention-no-system-class-name",
        name = "Custom class names must not match Salesforce system class names",
        description = "Custom classes sharing names with Salesforce system types create ambiguous references that confuse both developers and the compiler",
        tags = {"convention", "naming"},
        priority = Priority.MAJOR
)
public class NoSystemClassNameCheck extends BaseCheck {

    private static final Set<String> SYSTEM_NAMES = Set.of(
            "Account", "Contact", "Lead", "Opportunity", "User", "Trigger", "Task", "Event",
            "Case", "Campaign", "Contract", "Quote", "Order", "Product2", "PricebookEntry",
            "Attachment", "ContentVersion", "FeedItem", "List", "Set", "Map", "String", "Integer",
            "Boolean", "Decimal", "Date", "DateTime", "Id", "Blob", "Object", "Type", "System", "Schema"
    );
    private static final Pattern CLASS_NAME = Pattern.compile("\\bclass\\s+(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        Matcher m = CLASS_NAME.matcher(content);
        while (m.find()) {
            String name = m.group(1);
            if (SYSTEM_NAMES.contains(name)) {
                addIssue(lineOf(content, m.start()), "Custom class name must not match system class '" + name + "'.");
            }
        }
    }

    private static int lineOf(String content, int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') line++;
        }
        return line;
    }
}
