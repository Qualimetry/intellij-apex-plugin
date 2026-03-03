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

import com.qualimetry.sonar.apex.analyzer.visitor.ApexContentHelper;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Rethrown exceptions must preserve the original stack trace.
 * Flags when {@code getMessage()} is used but the exception object itself is not passed as a cause.
 */
@Rule(
        key = "qa-error-handling-preserve-stack-trace",
        name = "Rethrown exceptions must preserve the original stack trace",
        description = "Creating a new exception without passing the original as the cause discards the stack trace, making root-cause analysis impossible",
        tags = {"error-handling", "cert"},
        priority = Priority.MAJOR
)
public class PreserveStackTraceCheck extends BaseCheck {

    private static final Pattern CATCH_WITH_VAR = Pattern.compile("\\bcatch\\s*\\(\\s*\\w+\\s+(\\w+)\\s*\\)");
    private static final Pattern THROW_NEW = Pattern.compile("\\bthrow\\s+new\\s+\\w+\\s*\\(");
    private static final Pattern GET_MESSAGE = Pattern.compile("getMessage\\s*\\(");

    @Override
    public void scan(InputFile file, String content) {
        String[] lines = prepareLines(content);
        boolean inCatch = false;
        int braceCount = 0;
        String catchVar = null;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int opens = ApexContentHelper.countChar(line, '{');
            int closes = ApexContentHelper.countChar(line, '}');

            if (inCatch) {
                braceCount += opens - closes;
                if (THROW_NEW.matcher(line).find() && GET_MESSAGE.matcher(line).find()) {
                    if (!passesCause(line, catchVar)) {
                        addIssue(i + 1, "Rethrown exceptions must preserve the original stack trace.");
                    }
                }
                if (braceCount <= 0) {
                    inCatch = false;
                    catchVar = null;
                }
            }

            if (!inCatch) {
                Matcher m = CATCH_WITH_VAR.matcher(line);
                if (m.find()) {
                    catchVar = m.group(1);
                    inCatch = true;
                    braceCount = opens;
                }
            }
        }
    }

    private boolean passesCause(String line, String varName) {
        return Pattern.compile(",\\s*" + Pattern.quote(varName) + "\\s*[,)]").matcher(line).find()
                || Pattern.compile("initCause\\s*\\(\\s*" + Pattern.quote(varName)).matcher(line).find();
    }
}
