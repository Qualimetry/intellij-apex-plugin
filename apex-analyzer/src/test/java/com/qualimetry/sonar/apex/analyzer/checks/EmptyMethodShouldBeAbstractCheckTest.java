/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptyMethodShouldBeAbstractCheckTest {

    private EmptyMethodShouldBeAbstractCheck check;

    @BeforeEach
    void setUp() { check = new EmptyMethodShouldBeAbstractCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-empty-method-should-be-abstract"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-empty-method-should-be-abstract", 2, 3); }
}
