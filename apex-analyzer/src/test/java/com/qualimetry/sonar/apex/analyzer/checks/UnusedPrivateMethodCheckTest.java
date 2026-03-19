/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedPrivateMethodCheckTest {

    private UnusedPrivateMethodCheck check;

    @BeforeEach
    void setUp() { check = new UnusedPrivateMethodCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-private-method"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-private-method", 14); }
}
