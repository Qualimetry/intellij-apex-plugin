/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SystemAssertForBooleanCheckTest {

    private SystemAssertForBooleanCheck check;

    @BeforeEach
    void setUp() { check = new SystemAssertForBooleanCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-system-assert-for-boolean"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-system-assert-for-boolean", 5, 6, 11); }
}
