/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class FieldDeclarationPositionCheckTest {
    private FieldDeclarationPositionCheck check;

    @BeforeEach
    void setUp() { check = new FieldDeclarationPositionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-field-declaration-position"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-field-declaration-position", 6, 7); }
}
