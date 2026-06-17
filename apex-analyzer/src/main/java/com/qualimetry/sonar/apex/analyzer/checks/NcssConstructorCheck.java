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
 * Constructor non-commenting source statements must not exceed threshold.
 */
@Rule(
        key = "qa-complexity-ncss-constructor",
        name = "Constructor NCSS must not exceed threshold",
        description = "Constructors with too many statements are doing excessive initialization; extract to factory methods",
        tags = {"brain-overload"},
        priority = Priority.MAJOR
)
public class NcssConstructorCheck extends BaseCheck {

    private static final int MAX_NCSS = 15;

    @Override
    public void scan(InputFile file, String content) {
        content = content.replace("\r", "");
        String className = null;
        for (String line : prepareLines(content)) {
            if (line.matches(".*\\bclass\\s+\\w+.*")) {
                var matcher = Pattern.compile("\\bclass\\s+(\\w+)").matcher(line);
                if (matcher.find()) className = matcher.group(1);
                break;
            }
        }
        if (className == null) return;
        String[] lines = prepareLines(content);
        int depth = 0;
        int ncss = 0;
        int constructorStart = -1;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            if (depth == 1 && (trimmed.startsWith(className + "(") || trimmed.contains(" " + className + "(") || trimmed.contains(className + "("))) {
                constructorStart = i + 1;
            }
            if (constructorStart > 0 && depth > 1) {
                if (!trimmed.isEmpty() && !trimmed.startsWith("//") && !trimmed.startsWith("*")) ncss++;
            }
            depth += countChar(line, '{') - countChar(line, '}');
            if (depth == 1 && constructorStart > 0) {
                if (ncss > MAX_NCSS) addIssue(constructorStart, "Constructor NCSS is " + ncss + " (max " + MAX_NCSS + ").");
                constructorStart = -1;
                ncss = 0;
            }
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
