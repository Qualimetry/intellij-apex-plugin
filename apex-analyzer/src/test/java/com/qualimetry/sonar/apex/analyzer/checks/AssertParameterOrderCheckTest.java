/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AssertParameterOrderCheckTest {

    private AssertParameterOrderCheck check;

    @BeforeEach
    void setUp() { check = new AssertParameterOrderCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-assert-parameter-order"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-assert-parameter-order", 12); }
}
