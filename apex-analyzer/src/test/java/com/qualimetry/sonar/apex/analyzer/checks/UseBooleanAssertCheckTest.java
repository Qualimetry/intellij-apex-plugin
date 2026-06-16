/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseBooleanAssertCheckTest {

    private UseBooleanAssertCheck check;

    @BeforeEach
    void setUp() { check = new UseBooleanAssertCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-use-boolean-assert"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-use-boolean-assert", 5, 6, 11); }
}
