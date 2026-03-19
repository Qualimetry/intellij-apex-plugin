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
 * Controller constructors and actions must not perform DML operations.
 */
@Rule(
        key = "qa-security-no-dml-in-controller-init",
        name = "Controller constructors/actions must not perform DML",
        description = "DML in constructors runs on every page load, causing unintended data changes and CSRF exposure",
        tags = {"cwe", "owasp-a8"},
        priority = Priority.MAJOR
)
public class NoDmlInControllerInitCheck extends BaseCheck {

    private static final Pattern DML = Pattern.compile("\\b(?:insert|update|delete|upsert)\\s+");
    private static final Pattern CONTROLLER = Pattern.compile("extends\\s+\\w*Controller");

    @Override
    public void scan(InputFile file, String content) {
        boolean isController = CONTROLLER.matcher(content).find();
        if (!isController) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (DML.matcher(lines[i]).find()) {
                addIssue(i + 1, "Do not perform DML in controller constructor or action methods.");
            }
        }
    }
}
