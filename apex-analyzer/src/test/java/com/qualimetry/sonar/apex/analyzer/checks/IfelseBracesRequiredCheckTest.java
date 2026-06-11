/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class IfelseBracesRequiredCheckTest {
    private IfelseBracesRequiredCheck check;

    @BeforeEach
    void setUp() { check = new IfelseBracesRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-ifelse-braces-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-ifelse-braces-required", 6, 12); }
}
