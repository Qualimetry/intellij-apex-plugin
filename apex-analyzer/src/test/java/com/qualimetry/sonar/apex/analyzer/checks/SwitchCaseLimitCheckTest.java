/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SwitchCaseLimitCheckTest {

    private SwitchCaseLimitCheck check;

    @BeforeEach
    void setUp() { check = new SwitchCaseLimitCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-switch-case-limit"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-switch-case-limit", 5); }
}
