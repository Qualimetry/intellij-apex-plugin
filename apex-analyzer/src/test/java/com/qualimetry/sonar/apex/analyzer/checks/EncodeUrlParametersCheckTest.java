/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EncodeUrlParametersCheckTest {

    private EncodeUrlParametersCheck check;

    @BeforeEach
    void setUp() { check = new EncodeUrlParametersCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-encode-url-parameters"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-encode-url-parameters", 3); }
}
