/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MaxReturnStatementsCheckTest {

    private MaxReturnStatementsCheck check;

    @BeforeEach
    void setUp() { check = new MaxReturnStatementsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-max-return-statements"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-max-return-statements", 1); }
}
