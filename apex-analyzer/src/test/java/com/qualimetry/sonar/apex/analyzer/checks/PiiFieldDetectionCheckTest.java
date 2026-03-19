/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class PiiFieldDetectionCheckTest {

    private PiiFieldDetectionCheck check;

    @BeforeEach
    void setUp() { check = new PiiFieldDetectionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-pii-field-detection"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-pii-field-detection", 2, 3, 4, 5, 6, 7, 8); }
}
