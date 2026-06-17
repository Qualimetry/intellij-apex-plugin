/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnnecessaryReturnCheckTest {

    private UnnecessaryReturnCheck check;

    @BeforeEach
    void setUp() { check = new UnnecessaryReturnCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-unnecessary-return"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-unnecessary-return", 5, 13, 17, 22); }
}
