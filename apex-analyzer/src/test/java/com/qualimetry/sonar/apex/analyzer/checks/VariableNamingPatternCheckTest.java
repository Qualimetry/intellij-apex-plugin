/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class VariableNamingPatternCheckTest {

    private VariableNamingPatternCheck check;

    @BeforeEach
    void setUp() { check = new VariableNamingPatternCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-variable-naming-pattern"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-variable-naming-pattern", 11); }
}
