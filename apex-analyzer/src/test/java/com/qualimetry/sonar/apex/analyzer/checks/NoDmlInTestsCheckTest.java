/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoDmlInTestsCheckTest {

    private NoDmlInTestsCheck check;

    @BeforeEach
    void setUp() { check = new NoDmlInTestsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-no-dml-in-tests"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-no-dml-in-tests", 5, 7, 14, 17); }
}
