/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class PreserveExceptionInfoCheckTest {

    private PreserveExceptionInfoCheck check;

    @BeforeEach
    void setUp() { check = new PreserveExceptionInfoCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-preserve-exception-info"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-preserve-exception-info", 7, 16, 24); }
}
