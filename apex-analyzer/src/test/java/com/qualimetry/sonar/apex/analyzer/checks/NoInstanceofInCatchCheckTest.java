/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoInstanceofInCatchCheckTest {

    private NoInstanceofInCatchCheck check;

    @BeforeEach
    void setUp() { check = new NoInstanceofInCatchCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-instanceof-in-catch"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-instanceof-in-catch", 6, 8, 20); }
}
