/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NestedIfDepthCheckTest {

    private NestedIfDepthCheck check;

    @BeforeEach
    void setUp() { check = new NestedIfDepthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-nested-if-depth"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-nested-if-depth", 9); }
}
