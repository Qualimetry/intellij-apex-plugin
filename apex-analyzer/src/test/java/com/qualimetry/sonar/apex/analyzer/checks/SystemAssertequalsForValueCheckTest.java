/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SystemAssertequalsForValueCheckTest {

    private SystemAssertequalsForValueCheck check;

    @BeforeEach
    void setUp() { check = new SystemAssertequalsForValueCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-system-assertequals-for-value"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-system-assertequals-for-value", 5, 6, 11); }
}
