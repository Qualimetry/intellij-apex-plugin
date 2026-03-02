/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CorrectAssertUsageCheckTest {

    private CorrectAssertUsageCheck check;

    @BeforeEach
    void setUp() { check = new CorrectAssertUsageCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-correct-assert-usage"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-correct-assert-usage", 5, 6, 7, 8); }
}
