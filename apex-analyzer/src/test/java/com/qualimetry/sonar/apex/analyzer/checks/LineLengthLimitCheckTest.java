/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class LineLengthLimitCheckTest {

    private LineLengthLimitCheck check;

    @BeforeEach
    void setUp() { check = new LineLengthLimitCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-line-length-limit"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-line-length-limit", 10); }
}
