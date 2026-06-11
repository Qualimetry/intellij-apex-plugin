/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoCleartextTransmissionCheckTest {

    private NoCleartextTransmissionCheck check;

    @BeforeEach
    void setUp() { check = new NoCleartextTransmissionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-no-cleartext-transmission"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-no-cleartext-transmission", 4, 12); }
}
