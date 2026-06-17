/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnnecessaryLocalBeforeReturnCheckTest {

    private UnnecessaryLocalBeforeReturnCheck check;

    @BeforeEach
    void setUp() { check = new UnnecessaryLocalBeforeReturnCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-unnecessary-local-before-return"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-unnecessary-local-before-return", 4, 9, 14); }
}
