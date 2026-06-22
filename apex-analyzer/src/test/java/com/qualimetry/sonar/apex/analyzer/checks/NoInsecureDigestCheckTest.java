/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoInsecureDigestCheckTest {

    private NoInsecureDigestCheck check;

    @BeforeEach
    void setUp() { check = new NoInsecureDigestCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-no-insecure-digest"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-no-insecure-digest", 3, 8, 12); }
}
