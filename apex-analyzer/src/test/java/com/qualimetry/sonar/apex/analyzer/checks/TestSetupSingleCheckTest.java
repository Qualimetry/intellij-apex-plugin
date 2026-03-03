/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class TestSetupSingleCheckTest {

    private TestSetupSingleCheck check;

    @BeforeEach
    void setUp() { check = new TestSetupSingleCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-test-setup-single"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-test-setup-single", 9); }
}
