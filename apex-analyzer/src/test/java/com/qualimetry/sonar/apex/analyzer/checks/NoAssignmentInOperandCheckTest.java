/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoAssignmentInOperandCheckTest {
    private NoAssignmentInOperandCheck check;

    @BeforeEach
    void setUp() { check = new NoAssignmentInOperandCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-assignment-in-operand"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-assignment-in-operand", 4, 9); }
}
