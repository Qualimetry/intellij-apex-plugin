/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoExceptionFlowControlCheckTest {

    private NoExceptionFlowControlCheck check;

    @BeforeEach
    void setUp() { check = new NoExceptionFlowControlCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-exception-flow-control"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-exception-flow-control", 4, 11); }
}
