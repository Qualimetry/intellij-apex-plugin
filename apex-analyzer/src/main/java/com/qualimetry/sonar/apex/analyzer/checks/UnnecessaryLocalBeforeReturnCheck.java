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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Remove variables used only in an immediate return.
 */
@Rule(
        key = "qa-design-unnecessary-local-before-return",
        name = "Remove variables used only in an immediate return",
        description = "Assigning a value to a local variable only to return it on the next line adds an unnecessary indirection that reduces readability",
        tags = {"confusing", "clumsy"},
        priority = Priority.MINOR
)
public class UnnecessaryLocalBeforeReturnCheck extends BaseCheck {

    private static final Pattern LOCAL_DECL = Pattern.compile(
            "(?i)^\\s*(?:final\\s+)?([A-Z]\\w*(?:<[^>]+>)?(?:\\[\\])?)\\s+(\\w+)\\s*=\\s*.+;\\s*$");

    private static final Pattern RETURN_VAR = Pattern.compile(
            "^\\s*return\\s+(\\w+)\\s*;\\s*$");

    @Override
    public void scan(InputFile file, String content) {
        String[] strippedLines = prepareLines(content);

        for (int i = 0; i < strippedLines.length - 1; i++) {
            if (strippedLines[i].trim().isEmpty()) {
                continue;
            }

            Matcher declMatcher = LOCAL_DECL.matcher(strippedLines[i]);
            if (!declMatcher.find()) {
                continue;
            }

            String varName = declMatcher.group(2);

            int nextNonBlank = -1;
            for (int j = i + 1; j < strippedLines.length; j++) {
                if (!strippedLines[j].trim().isEmpty()) {
                    nextNonBlank = j;
                    break;
                }
            }
            if (nextNonBlank < 0) {
                continue;
            }

            Matcher retMatcher = RETURN_VAR.matcher(strippedLines[nextNonBlank]);
            if (!retMatcher.find() || !retMatcher.group(1).equals(varName)) {
                continue;
            }

            String methodBody = extractEnclosingBlock(strippedLines, i);
            if (methodBody == null) {
                continue;
            }

            int usageCount = 0;
            Matcher usage = Pattern.compile("\\b" + Pattern.quote(varName) + "\\b").matcher(methodBody);
            while (usage.find()) {
                usageCount++;
            }

            if (usageCount == 2) {
                addIssue(i + 1,
                        "Variable '" + varName + "' is assigned and immediately returned; return the expression directly.");
            }
        }
    }

    private String extractEnclosingBlock(String[] lines, int targetLine) {
        int depth = 0;
        int blockStart = -1;

        for (int i = targetLine; i >= 0; i--) {
            String line = lines[i];
            for (int j = line.length() - 1; j >= 0; j--) {
                char c = line.charAt(j);
                if (c == '}') {
                    depth++;
                } else if (c == '{') {
                    depth--;
                    if (depth < 0) {
                        blockStart = i;
                        break;
                    }
                }
            }
            if (blockStart >= 0) break;
        }

        if (blockStart < 0) return null;

        depth = 0;
        for (int i = blockStart; i < lines.length; i++) {
            for (char c : lines[i].toCharArray()) {
                if (c == '{') depth++;
                else if (c == '}') depth--;
            }
            if (depth == 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = blockStart; j <= i; j++) {
                    sb.append(lines[j]).append('\n');
                }
                return sb.toString();
            }
        }

        return null;
    }
}
