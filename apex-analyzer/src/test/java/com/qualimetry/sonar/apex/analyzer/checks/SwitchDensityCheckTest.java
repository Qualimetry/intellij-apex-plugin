/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SwitchDensityCheckTest {

    private SwitchDensityCheck check;

    @BeforeEach
    void setUp() { check = new SwitchDensityCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-switch-density"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-switch-density", 4); }
}
