/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AuraControllerNamingCheckTest {
    private AuraControllerNamingCheck check;

    @BeforeEach
    void setUp() { check = new AuraControllerNamingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-aura-controller-naming"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-aura-controller-naming", 1); }
}
