/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class WhileBracesRequiredCheckTest {

    private WhileBracesRequiredCheck check;

    @BeforeEach
    void setUp() { check = new WhileBracesRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-while-braces-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-while-braces-required", 11); }
}
