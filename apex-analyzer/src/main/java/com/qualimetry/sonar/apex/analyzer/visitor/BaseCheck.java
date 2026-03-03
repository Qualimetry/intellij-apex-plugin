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
package com.qualimetry.sonar.apex.analyzer.visitor;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for Apex analysis checks.
 * Subclasses implement {@link #scan(InputFile, String)} and report issues via {@link #addIssue(int, String)}.
 */
public abstract class BaseCheck {

    private final List<Issue> issues = new ArrayList<>();

    /**
     * Runs the check against the given file.
     *
     * @param file    the input file being analyzed
     * @param content the full text content of the file
     */
    public abstract void scan(InputFile file, String content);

    protected String getRuleKey() {
        Rule r = getClass().getAnnotation(Rule.class);
        return r != null ? r.key() : "unknown";
    }

    protected void addIssue(int line, String message) {
        issues.add(new Issue(line, message, getRuleKey()));
    }

    public List<Issue> getIssues() {
        return Collections.unmodifiableList(issues);
    }

    public void clearIssues() {
        issues.clear();
    }

    protected String[] prepareLines(String content) {
        return ApexContentHelper.splitLines(ApexContentHelper.stripCommentsAndStrings(content));
    }

    protected String stripContent(String content) {
        return ApexContentHelper.stripCommentsAndStrings(content);
    }

    /**
     * An issue reported by a check.
     *
     * @param line     1-based line number
     * @param message  human-readable description
     * @param ruleKey  the rule key that produced this issue
     */
    public record Issue(int line, String message, String ruleKey) {
    }
}
