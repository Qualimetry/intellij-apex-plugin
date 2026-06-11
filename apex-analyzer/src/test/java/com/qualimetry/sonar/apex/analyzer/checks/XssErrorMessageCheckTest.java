/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class XssErrorMessageCheckTest {

    private XssErrorMessageCheck check;

    @BeforeEach
    void setUp() { check = new XssErrorMessageCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-xss-error-message"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-xss-error-message", 3); }
}
