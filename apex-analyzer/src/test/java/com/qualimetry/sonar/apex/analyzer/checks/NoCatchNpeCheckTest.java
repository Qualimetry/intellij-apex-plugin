/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoCatchNpeCheckTest {

    private NoCatchNpeCheck check;

    @BeforeEach
    void setUp() { check = new NoCatchNpeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-catch-npe"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-catch-npe", 6, 15, 25); }
}
