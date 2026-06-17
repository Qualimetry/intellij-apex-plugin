/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoThrowInFinallyCheckTest {

    private NoThrowInFinallyCheck check;

    @BeforeEach
    void setUp() { check = new NoThrowInFinallyCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-throw-in-finally"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-throw-in-finally", 6, 17); }
}
