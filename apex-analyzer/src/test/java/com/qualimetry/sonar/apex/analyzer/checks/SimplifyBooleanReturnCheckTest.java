/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SimplifyBooleanReturnCheckTest {

    private SimplifyBooleanReturnCheck check;

    @BeforeEach
    void setUp() { check = new SimplifyBooleanReturnCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-simplify-boolean-return"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-simplify-boolean-return", 3, 6); }
}
