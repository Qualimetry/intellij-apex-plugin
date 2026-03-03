/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class RightBracePositionCheckTest {
    private RightBracePositionCheck check;

    @BeforeEach
    void setUp() { check = new RightBracePositionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-right-brace-position"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-right-brace-position", 4); }
}
