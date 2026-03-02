/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ReviewBatchCalloutsCheckTest {

    private ReviewBatchCalloutsCheck check;

    @BeforeEach
    void setUp() { check = new ReviewBatchCalloutsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-review-batch-callouts"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-review-batch-callouts", 1); }
}
