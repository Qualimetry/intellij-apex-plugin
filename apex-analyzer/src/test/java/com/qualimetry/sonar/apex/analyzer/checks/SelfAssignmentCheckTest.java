/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SelfAssignmentCheckTest {

    private SelfAssignmentCheck check;

    @BeforeEach
    void setUp() { check = new SelfAssignmentCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-self-assignment"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-self-assignment", 9, 10); }
}
