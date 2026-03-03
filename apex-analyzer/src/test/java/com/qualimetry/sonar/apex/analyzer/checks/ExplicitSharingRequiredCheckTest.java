/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ExplicitSharingRequiredCheckTest {

    private ExplicitSharingRequiredCheck check;

    @BeforeEach
    void setUp() { check = new ExplicitSharingRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-explicit-sharing-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-explicit-sharing-required", 1); }
}
