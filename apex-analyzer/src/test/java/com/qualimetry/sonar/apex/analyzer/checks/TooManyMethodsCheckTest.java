/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class TooManyMethodsCheckTest {

    private TooManyMethodsCheck check;

    @BeforeEach
    void setUp() { check = new TooManyMethodsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-too-many-methods"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-too-many-methods", 1); }
}
