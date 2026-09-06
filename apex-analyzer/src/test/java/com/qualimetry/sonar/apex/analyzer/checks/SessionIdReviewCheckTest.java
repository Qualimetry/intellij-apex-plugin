/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SessionIdReviewCheckTest {

    private SessionIdReviewCheck check;

    @BeforeEach
    void setUp() { check = new SessionIdReviewCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-session-id-review"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-session-id-review", 3, 9); }
}
