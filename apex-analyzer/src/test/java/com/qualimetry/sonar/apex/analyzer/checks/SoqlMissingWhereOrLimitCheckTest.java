/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SoqlMissingWhereOrLimitCheckTest {

    private SoqlMissingWhereOrLimitCheck check;

    @BeforeEach
    void setUp() { check = new SoqlMissingWhereOrLimitCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-soql-missing-where-or-limit"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-soql-missing-where-or-limit", 3, 7, 13); }
}
