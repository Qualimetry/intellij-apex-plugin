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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Verifier for Apex checks: loads fixture content, runs the check, and asserts issue count and line numbers.
 */
public final class CheckVerifier {

    private static final String FIXTURE_BASE = "/checks/";

    private CheckVerifier() {
    }

    /**
     * Loads fixture content from classpath: checks/{ruleKey}/compliant.cls or noncompliant.cls.
     */
    public static String loadFixture(String ruleKey, String filename) {
        String path = FIXTURE_BASE + ruleKey + "/" + filename;
        try (InputStream is = CheckVerifier.class.getResourceAsStream(path)) {
            assertThat(is).as("Fixture not found: %s", path).isNotNull();
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AssertionError("Failed to load fixture: " + path, e);
        }
    }

    /**
     * Creates a mock InputFile. Checks that only use content may not need file metadata.
     */
    public static InputFile mockInputFile() {
        return mock(InputFile.class);
    }

    /**
     * Runs the check on the given content and returns the reported issues.
     */
    public static List<BaseCheck.Issue> runCheck(BaseCheck check, String content) {
        check.clearIssues();
        check.scan(mockInputFile(), content);
        return check.getIssues();
    }

    /**
     * Verifies that the compliant fixture produces no issues.
     */
    public static void verifyCompliant(BaseCheck check, String ruleKey) {
        verifyCompliant(check, ruleKey, "compliant.cls");
    }

    /**
     * Verifies that the compliant fixture (given filename) produces no issues.
     * Use for trigger rules: verifyCompliant(check, ruleKey, "compliant.trigger").
     */
    public static void verifyCompliant(BaseCheck check, String ruleKey, String compliantFileName) {
        String content = loadFixture(ruleKey, compliantFileName);
        List<BaseCheck.Issue> issues = runCheck(check, content);
        assertThat(issues).as("Compliant fixture should raise no issues").isEmpty();
    }

    /**
     * Verifies that the noncompliant fixture produces exactly the expected line numbers.
     */
    public static void verifyNonCompliant(BaseCheck check, String ruleKey, int... expectedLines) {
        verifyNonCompliant(check, ruleKey, "noncompliant.cls", expectedLines);
    }

    /**
     * Verifies that the noncompliant fixture (given filename) produces exactly the expected line numbers.
     * Use for trigger rules: verifyNonCompliant(check, ruleKey, "noncompliant.trigger", 3);
     */
    public static void verifyNonCompliant(BaseCheck check, String ruleKey, String noncompliantFileName, int... expectedLines) {
        String content = loadFixture(ruleKey, noncompliantFileName);
        List<BaseCheck.Issue> issues = runCheck(check, content);
        assertThat(issues).as("Expected %d issue(s)", expectedLines.length).hasSize(expectedLines.length);
        List<Integer> actualLines = issues.stream().map(BaseCheck.Issue::line).sorted().collect(Collectors.toList());
        List<Integer> expected = java.util.Arrays.stream(expectedLines).sorted().boxed().collect(Collectors.toList());
        assertThat(actualLines).as("Issue line numbers").containsExactlyElementsOf(expected);
    }
}
