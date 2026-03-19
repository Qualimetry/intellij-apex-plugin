/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class RedundantCaseChangeCheckTest {

    private RedundantCaseChangeCheck check;

    @BeforeEach
    void setUp() { check = new RedundantCaseChangeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-redundant-case-change"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-redundant-case-change", 3, 8, 16); }
}
