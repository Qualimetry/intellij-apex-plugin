/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class OutdatedApiVersionCheckTest {

    private OutdatedApiVersionCheck check;

    @BeforeEach
    void setUp() { check = new OutdatedApiVersionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-outdated-api-version"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-outdated-api-version", 1); }
}
