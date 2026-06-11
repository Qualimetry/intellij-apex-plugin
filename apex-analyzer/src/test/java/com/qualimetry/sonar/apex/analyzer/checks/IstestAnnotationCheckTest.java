/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class IstestAnnotationCheckTest {

    private IstestAnnotationCheck check;

    @BeforeEach
    void setUp() { check = new IstestAnnotationCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-istest-annotation"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-istest-annotation", 1); }
}
