/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MaxAssertsPerTestCheckTest {

    private MaxAssertsPerTestCheck check;

    @BeforeEach
    void setUp() { check = new MaxAssertsPerTestCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-max-asserts-per-test"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-max-asserts-per-test", 1); }
}
