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
 * Annotation does not exist in Apex.
 */
@Rule(
        key = "qa-error-nonexistent-annotation",
        name = "Annotation does not exist in Apex",
        description = "Detects annotations not recognized by the Apex language, such as @Override or @Nullable, indicating a typo or invalid API usage.",
        tags = {"error-prone", "convention"},
        priority = Priority.MAJOR
)
public class NonexistentAnnotationCheck extends BaseCheck {

    private static final Set<String> VALID_ANNOTATIONS = Set.of(
            "AuraEnabled", "InvocableMethod", "InvocableVariable", "Future", "TestSetup", "IsTest",
            "Deprecated", "SuppressWarnings", "RestResource", "HttpDelete", "HttpGet", "HttpPatch", "HttpPost", "HttpPut",
            "ReadOnly", "NamespaceAccessible", "ManagedShared", "RemoteAction"
    );
    private static final Pattern AT_ANNOTATION = Pattern.compile("@(\\w+)");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = AT_ANNOTATION.matcher(lines[i]);
            while (m.find()) {
                String ann = m.group(1);
                if (!VALID_ANNOTATIONS.contains(ann)) {
                    addIssue(i + 1, "Annotation does not exist in Apex: @" + ann + ".");
                    break;
                }
            }
        }
    }
}
