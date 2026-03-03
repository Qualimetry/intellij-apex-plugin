/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SimplifyTernaryWithLogicalCheckTest {

    private SimplifyTernaryWithLogicalCheck check;

    @BeforeEach
    void setUp() { check = new SimplifyTernaryWithLogicalCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-simplify-ternary-with-logical"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-simplify-ternary-with-logical", 3, 7, 11); }
}
