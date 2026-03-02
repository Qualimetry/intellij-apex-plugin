/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MethodShouldBeStaticCheckTest {

    private MethodShouldBeStaticCheck check;

    @BeforeEach
    void setUp() { check = new MethodShouldBeStaticCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-method-should-be-static"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-method-should-be-static", 4, 8); }
}
