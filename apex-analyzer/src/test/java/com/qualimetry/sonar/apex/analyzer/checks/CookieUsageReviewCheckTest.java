/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CookieUsageReviewCheckTest {

    private CookieUsageReviewCheck check;

    @BeforeEach
    void setUp() { check = new CookieUsageReviewCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-cookie-usage-review"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-cookie-usage-review", 3, 4); }
}
