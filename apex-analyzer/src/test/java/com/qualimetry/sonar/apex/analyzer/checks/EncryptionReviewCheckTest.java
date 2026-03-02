/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EncryptionReviewCheckTest {

    private EncryptionReviewCheck check;

    @BeforeEach
    void setUp() { check = new EncryptionReviewCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-encryption-review"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-encryption-review", 3, 7, 11); }
}
