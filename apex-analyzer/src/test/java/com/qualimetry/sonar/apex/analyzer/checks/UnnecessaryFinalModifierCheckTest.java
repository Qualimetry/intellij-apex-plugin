/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnnecessaryFinalModifierCheckTest {

    private UnnecessaryFinalModifierCheck check;

    @BeforeEach
    void setUp() { check = new UnnecessaryFinalModifierCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-unnecessary-final-modifier"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-unnecessary-final-modifier", 9, 19, 23); }
}
