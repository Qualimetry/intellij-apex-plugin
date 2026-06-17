/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnassignedVariableCheckTest {

    private UnassignedVariableCheck check;

    @BeforeEach
    void setUp() { check = new UnassignedVariableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-unassigned-variable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-unassigned-variable", 7, 7); }
}
