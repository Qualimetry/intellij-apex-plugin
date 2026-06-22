/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ReviewOutboundEmailCheckTest {

    private ReviewOutboundEmailCheck check;

    @BeforeEach
    void setUp() { check = new ReviewOutboundEmailCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-review-outbound-email"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-review-outbound-email", 7, 14); }
}
