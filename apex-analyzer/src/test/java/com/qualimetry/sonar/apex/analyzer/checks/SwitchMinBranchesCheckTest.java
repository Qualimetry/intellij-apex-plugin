/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SwitchMinBranchesCheckTest {

    private SwitchMinBranchesCheck check;

    @BeforeEach
    void setUp() { check = new SwitchMinBranchesCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-switch-min-branches"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-switch-min-branches", 3, 15); }
}
