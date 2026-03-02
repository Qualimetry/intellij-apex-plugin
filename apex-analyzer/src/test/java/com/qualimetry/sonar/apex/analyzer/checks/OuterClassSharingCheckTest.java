/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class OuterClassSharingCheckTest {

    private OuterClassSharingCheck check;

    @BeforeEach
    void setUp() { check = new OuterClassSharingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-outer-class-sharing"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-outer-class-sharing", 1); }
}
