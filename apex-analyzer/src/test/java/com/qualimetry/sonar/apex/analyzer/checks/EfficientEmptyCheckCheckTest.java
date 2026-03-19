/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EfficientEmptyCheckCheckTest {

    private EfficientEmptyCheckCheck check;

    @BeforeEach
    void setUp() { check = new EfficientEmptyCheckCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-efficient-empty-check"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-efficient-empty-check", 13); }
}
