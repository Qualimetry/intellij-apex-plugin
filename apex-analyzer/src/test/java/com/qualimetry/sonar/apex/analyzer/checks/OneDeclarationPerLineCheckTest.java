/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class OneDeclarationPerLineCheckTest {
    private OneDeclarationPerLineCheck check;

    @BeforeEach
    void setUp() { check = new OneDeclarationPerLineCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-one-declaration-per-line"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-one-declaration-per-line", 3, 7); }
}
