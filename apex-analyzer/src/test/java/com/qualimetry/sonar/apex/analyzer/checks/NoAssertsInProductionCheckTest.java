/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoAssertsInProductionCheckTest {

    private NoAssertsInProductionCheck check;

    @BeforeEach
    void setUp() { check = new NoAssertsInProductionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-no-asserts-in-production"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-no-asserts-in-production", 3, 4, 9); }
}
