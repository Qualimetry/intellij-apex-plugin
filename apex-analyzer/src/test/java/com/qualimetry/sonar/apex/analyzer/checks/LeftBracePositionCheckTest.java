/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class LeftBracePositionCheckTest {
    private LeftBracePositionCheck check;

    @BeforeEach
    void setUp() { check = new LeftBracePositionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-left-brace-position"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-left-brace-position", 3); }
}
