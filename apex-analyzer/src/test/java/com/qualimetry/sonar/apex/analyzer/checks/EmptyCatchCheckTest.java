/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptyCatchCheckTest {

    private EmptyCatchCheck check;

    @BeforeEach
    void setUp() { check = new EmptyCatchCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-empty-catch"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-empty-catch", 5, 8); }
}
