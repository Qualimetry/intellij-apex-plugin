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
 * Queueable jobs should attach a Finalizer for cleanup and monitoring.
 */
@Rule(
        key = "qa-bestpractice-queueable-finalizer",
        name = "Queueable jobs should attach a Finalizer",
        description = "Flags Queueable classes that do not attach a Finalizer for cleanup, logging, or retry on job completion.",
        tags = {"best-practice", "async", "salesforce"},
        priority = Priority.MAJOR
)
public class QueueableFinalizerCheck extends BaseCheck {

    private static final Pattern IMPLEMENTS_QUEUEABLE = Pattern.compile("implements\\s+Queueable\\b");
    private static final Pattern SYSTEM_ATTACH_FINALIZER = Pattern.compile("System\\.attachFinalizer\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!IMPLEMENTS_QUEUEABLE.matcher(content).find()) return;
        if (SYSTEM_ATTACH_FINALIZER.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        for (int i = 0; i < lines.length; i++) {
            if (IMPLEMENTS_QUEUEABLE.matcher(lines[i]).find()) {
                addIssue(i + 1, "Queueable jobs should attach a Finalizer.");
                return;
            }
        }
    }
}
