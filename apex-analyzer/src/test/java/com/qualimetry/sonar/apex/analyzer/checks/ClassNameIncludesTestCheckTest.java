/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ClassNameIncludesTestCheckTest {

    private ClassNameIncludesTestCheck check;

    @BeforeEach
    void setUp() { check = new ClassNameIncludesTestCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-class-name-includes-test"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-class-name-includes-test", 2); }
}
