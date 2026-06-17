/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoRefererHeaderCheckTest {

    private NoRefererHeaderCheck check;

    @BeforeEach
    void setUp() { check = new NoRefererHeaderCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-no-referer-header"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-no-referer-header", 3, 10); }
}
