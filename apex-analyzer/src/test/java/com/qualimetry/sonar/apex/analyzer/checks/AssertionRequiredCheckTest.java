/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AssertionRequiredCheckTest {

    private AssertionRequiredCheck check;

    @BeforeEach
    void setUp() { check = new AssertionRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-assertion-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-assertion-required", 9, 15); }
}
