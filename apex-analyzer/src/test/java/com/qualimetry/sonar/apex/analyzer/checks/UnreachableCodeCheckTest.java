/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnreachableCodeCheckTest {

    private UnreachableCodeCheck check;

    @BeforeEach
    void setUp() { check = new UnreachableCodeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-unreachable-code"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-unreachable-code", 4, 8, 12); }
}
