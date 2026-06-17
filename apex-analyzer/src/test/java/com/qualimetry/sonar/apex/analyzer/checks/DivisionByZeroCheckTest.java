/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class DivisionByZeroCheckTest {

    private DivisionByZeroCheck check;

    @BeforeEach
    void setUp() { check = new DivisionByZeroCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-division-by-zero"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-division-by-zero", 3, 7, 14); }
}
