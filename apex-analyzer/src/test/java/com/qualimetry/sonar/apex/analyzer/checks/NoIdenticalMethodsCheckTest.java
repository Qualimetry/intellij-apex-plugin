/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoIdenticalMethodsCheckTest {

    private NoIdenticalMethodsCheck check;

    @BeforeEach
    void setUp() { check = new NoIdenticalMethodsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-identical-methods"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-identical-methods", 5, 9); }
}
