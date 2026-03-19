/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ForBracesRequiredCheckTest {

    private ForBracesRequiredCheck check;

    @BeforeEach
    void setUp() { check = new ForBracesRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-for-braces-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-for-braces-required", 6); }
}
