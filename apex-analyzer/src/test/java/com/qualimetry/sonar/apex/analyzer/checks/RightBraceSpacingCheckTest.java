/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class RightBraceSpacingCheckTest {
    private RightBraceSpacingCheck check;

    @BeforeEach
    void setUp() { check = new RightBraceSpacingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-right-brace-spacing"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-right-brace-spacing", 5, 13); }
}
