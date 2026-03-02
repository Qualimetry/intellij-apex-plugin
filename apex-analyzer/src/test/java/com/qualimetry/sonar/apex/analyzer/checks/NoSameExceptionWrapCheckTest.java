/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoSameExceptionWrapCheckTest {

    private NoSameExceptionWrapCheck check;

    @BeforeEach
    void setUp() { check = new NoSameExceptionWrapCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-same-exception-wrap"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-same-exception-wrap", 6, 14); }
}
