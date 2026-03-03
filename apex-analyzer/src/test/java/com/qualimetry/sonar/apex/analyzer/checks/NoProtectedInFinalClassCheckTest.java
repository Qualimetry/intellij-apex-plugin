/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoProtectedInFinalClassCheckTest {

    private NoProtectedInFinalClassCheck check;

    @BeforeEach
    void setUp() { check = new NoProtectedInFinalClassCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-protected-in-final-class"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-protected-in-final-class", 2, 3); }
}
