/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SoqlNoNullFilterCheckTest {

    private SoqlNoNullFilterCheck check;

    @BeforeEach
    void setUp() { check = new SoqlNoNullFilterCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-soql-no-null-filter"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-soql-no-null-filter", 3, 7, 11); }
}
