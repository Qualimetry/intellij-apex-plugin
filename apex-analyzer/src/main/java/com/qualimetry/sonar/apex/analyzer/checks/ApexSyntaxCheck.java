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
import org.sonar.api.rule.Severity;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

/**
 * Placeholder rule that verifies basic Apex syntax.
 */
@Rule(
        key = "qa-apex-syntax",
        name = "Apex syntax check",
        description = "Verifies basic Apex syntax",
        tags = {"apex", "syntax"},
        priority = Priority.CRITICAL
)
public class ApexSyntaxCheck extends BaseCheck {

    @Override
    public void scan(InputFile file, String content) {
        // Placeholder: syntax analysis will be implemented in a future version
    }
}
