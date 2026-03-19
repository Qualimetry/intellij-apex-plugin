/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoHardcodedEmailCheckTest {

    private NoHardcodedEmailCheck check;

    @BeforeEach
    void setUp() { check = new NoHardcodedEmailCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-no-hardcoded-email"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-no-hardcoded-email", 3, 8); }
}
