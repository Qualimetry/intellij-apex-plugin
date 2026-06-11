/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SoqlNegativeExpressionCheckTest {

    private SoqlNegativeExpressionCheck check;

    @BeforeEach
    void setUp() { check = new SoqlNegativeExpressionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-soql-negative-expression"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-soql-negative-expression", 3, 7, 11); }
}
