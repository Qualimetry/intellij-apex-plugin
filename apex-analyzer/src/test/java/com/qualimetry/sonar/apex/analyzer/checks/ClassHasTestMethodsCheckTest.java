/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ClassHasTestMethodsCheckTest {

    private ClassHasTestMethodsCheck check;

    @BeforeEach
    void setUp() { check = new ClassHasTestMethodsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-class-has-test-methods"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-class-has-test-methods", 2); }
}
