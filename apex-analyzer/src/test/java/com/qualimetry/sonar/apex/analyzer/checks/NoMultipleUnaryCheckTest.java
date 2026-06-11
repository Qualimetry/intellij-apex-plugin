/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoMultipleUnaryCheckTest {

    private NoMultipleUnaryCheck check;

    @BeforeEach
    void setUp() { check = new NoMultipleUnaryCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-multiple-unary"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-multiple-unary", 3, 7, 13, 17); }
}
