/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoReturnInFinallyCheckTest {

    private NoReturnInFinallyCheck check;

    @BeforeEach
    void setUp() { check = new NoReturnInFinallyCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-return-in-finally"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-return-in-finally", 9, 19); }
}
