/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ValidateRedirectUrlCheckTest {

    private ValidateRedirectUrlCheck check;

    @BeforeEach
    void setUp() { check = new ValidateRedirectUrlCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-validate-redirect-url"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-validate-redirect-url", 3); }
}
