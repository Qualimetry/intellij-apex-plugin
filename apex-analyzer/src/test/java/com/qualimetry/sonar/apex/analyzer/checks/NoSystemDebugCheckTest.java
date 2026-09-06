/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoSystemDebugCheckTest {

    private NoSystemDebugCheck check;

    @BeforeEach
    void setUp() { check = new NoSystemDebugCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-no-system-debug"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-no-system-debug", 3, 4, 6); }
}
