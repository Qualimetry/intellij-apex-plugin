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
 * Platform event publishing inside loops hits governor limits.
 */
@Rule(
        key = "qa-salesforce-event-publish-loop",
        name = "Platform event publishing inside loops hits governor limits",
        description = "Detects EventBus.publish() calls inside loop bodies that consume governor limits and should be batched.",
        tags = {"salesforce", "governor-limits", "performance"},
        priority = Priority.CRITICAL
)
public class EventPublishLoopCheck extends BaseCheck {

    private static final Pattern PUBLISH = Pattern.compile("\\.publish\\s*\\(");
    private static final Pattern LOOP = Pattern.compile("\\b(for|while)\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        if (!PUBLISH.matcher(content).find()) return;
        String[] lines = prepareLines(content);
        int depth = 0;
        boolean inLoop = false;
        int loopStart = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (LOOP.matcher(line).find()) {
                inLoop = true;
                loopStart = depth;
            }
            if (inLoop && PUBLISH.matcher(line).find()) {
                addIssue(i + 1, "Platform event publishing inside loops hits governor limits; collect and publish outside the loop.");
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth <= loopStart) inLoop = false;
        }
    }

    private static int countChar(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) n++;
        }
        return n;
    }
}
