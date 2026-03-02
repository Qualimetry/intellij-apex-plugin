/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoParameterReassignmentCheckTest {

    private NoParameterReassignmentCheck check;

    @BeforeEach
    void setUp() { check = new NoParameterReassignmentCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-parameter-reassignment"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-parameter-reassignment", 3, 9); }  // params before comma are tracked
}
