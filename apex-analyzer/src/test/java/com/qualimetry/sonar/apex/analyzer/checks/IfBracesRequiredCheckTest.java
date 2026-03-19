/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class IfBracesRequiredCheckTest {
    private IfBracesRequiredCheck check;

    @BeforeEach
    void setUp() { check = new IfBracesRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-if-braces-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-if-braces-required", 3, 5); }
}
