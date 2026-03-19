/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedModifierCheckTest {

    private UnusedModifierCheck check;

    @BeforeEach
    void setUp() { check = new UnusedModifierCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-modifier"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-modifier", 2, 3, 4); }
}
