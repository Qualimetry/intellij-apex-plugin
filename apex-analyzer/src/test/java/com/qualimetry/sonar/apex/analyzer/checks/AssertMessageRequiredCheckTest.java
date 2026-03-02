/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AssertMessageRequiredCheckTest {

    private AssertMessageRequiredCheck check;

    @BeforeEach
    void setUp() { check = new AssertMessageRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-assert-message-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-assert-message-required", 5, 6, 11, 12); }
}
