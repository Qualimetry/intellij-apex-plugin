/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoDoubleSuperCallCheckTest {

    private NoDoubleSuperCallCheck check;

    @BeforeEach
    void setUp() { check = new NoDoubleSuperCallCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-double-super-call"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-double-super-call", 4, 11); }
}
