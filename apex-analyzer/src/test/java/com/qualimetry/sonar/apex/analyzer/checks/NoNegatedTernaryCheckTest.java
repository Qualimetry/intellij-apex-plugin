/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoNegatedTernaryCheckTest {

    private NoNegatedTernaryCheck check;

    @BeforeEach
    void setUp() { check = new NoNegatedTernaryCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-negated-ternary"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-negated-ternary", 3, 7, 11); }
}
