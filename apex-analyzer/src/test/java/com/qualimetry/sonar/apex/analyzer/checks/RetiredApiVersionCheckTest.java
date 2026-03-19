/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class RetiredApiVersionCheckTest {

    private RetiredApiVersionCheck check;

    @BeforeEach
    void setUp() { check = new RetiredApiVersionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-retired-api-version"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-retired-api-version", 1); }
}
