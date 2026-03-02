/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoCatchThrowableCheckTest {

    private NoCatchThrowableCheck check;

    @BeforeEach
    void setUp() { check = new NoCatchThrowableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-catch-throwable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-catch-throwable", 5, 13, 23); }
}
