/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoNullAssignmentCheckTest {

    private NoNullAssignmentCheck check;

    @BeforeEach
    void setUp() { check = new NoNullAssignmentCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-null-assignment"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-null-assignment", 3, 4, 8); }
}
