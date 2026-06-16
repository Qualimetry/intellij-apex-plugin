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

/**
 * Controller getter methods must not execute SOQL.
 */
@Rule(
        key = "qa-salesforce-no-soql-in-getter",
        name = "Controller getter methods must not execute SOQL",
        description = "Getters are called multiple times per page render, causing repeated SOQL queries that exhaust governor limits",
        tags = {"performance"},
        priority = Priority.MINOR
)
public class NoSoqlInGetterCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        if (!content.contains("SELECT") || !content.contains("FROM")) return;
        if (!content.contains("get") || !content.contains("(")) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("SELECT") && lines[i].contains("FROM")) {
                for (int j = 0; j < i; j++) {
                    String prev = lines[j];
                    if (prev.contains("(") && (prev.contains(" get ") || prev.contains(" get") && prev.contains("()"))) {
                        addIssue(i + 1, "Do not execute SOQL in getter methods; use a setter or load data in the controller constructor.");
                        return;
                    }
                }
            }
        }
    }
}
