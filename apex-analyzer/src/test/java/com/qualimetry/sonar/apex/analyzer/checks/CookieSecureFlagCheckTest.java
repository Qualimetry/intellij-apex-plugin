/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CookieSecureFlagCheckTest {

    private CookieSecureFlagCheck check;

    @BeforeEach
    void setUp() { check = new CookieSecureFlagCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-cookie-secure-flag"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-cookie-secure-flag", 3, 8); }
}
