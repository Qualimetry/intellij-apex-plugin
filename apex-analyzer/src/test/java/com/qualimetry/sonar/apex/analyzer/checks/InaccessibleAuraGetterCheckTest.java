/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class InaccessibleAuraGetterCheckTest {

    private InaccessibleAuraGetterCheck check;

    @BeforeEach
    void setUp() { check = new InaccessibleAuraGetterCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-inaccessible-aura-getter"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-inaccessible-aura-getter", 4, 7); }
}
