/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoWrapperForConversionCheckTest {

    private NoWrapperForConversionCheck check;

    @BeforeEach
    void setUp() { check = new NoWrapperForConversionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-no-wrapper-for-conversion"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-no-wrapper-for-conversion", 3, 8, 13); }
}
