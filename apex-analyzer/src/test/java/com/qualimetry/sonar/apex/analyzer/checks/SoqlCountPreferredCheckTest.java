/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SoqlCountPreferredCheckTest {

    private SoqlCountPreferredCheck check;

    @BeforeEach
    void setUp() { check = new SoqlCountPreferredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-soql-count-preferred"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-soql-count-preferred", 10); }
}
