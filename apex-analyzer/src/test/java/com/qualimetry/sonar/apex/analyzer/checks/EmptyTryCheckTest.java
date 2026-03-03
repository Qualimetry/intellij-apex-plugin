/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptyTryCheckTest {

    private EmptyTryCheck check;

    @BeforeEach
    void setUp() { check = new EmptyTryCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-empty-try"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-empty-try", 3, 8, 16); }
}
