/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SwitchWhenElseCheckTest {

    private SwitchWhenElseCheck check;

    @BeforeEach
    void setUp() { check = new SwitchWhenElseCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-switch-when-else"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-switch-when-else", 5); }
}
