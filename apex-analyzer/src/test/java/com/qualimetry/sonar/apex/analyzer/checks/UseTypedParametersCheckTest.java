/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseTypedParametersCheckTest {

    private UseTypedParametersCheck check;

    @BeforeEach
    void setUp() { check = new UseTypedParametersCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-use-typed-parameters"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-use-typed-parameters", 2, 7); }
}
