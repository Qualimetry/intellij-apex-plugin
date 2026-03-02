/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class BrokenNullCheckCheckTest {

    private BrokenNullCheckCheck check;

    @BeforeEach
    void setUp() { check = new BrokenNullCheckCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-broken-null-check"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-broken-null-check", 3, 6); }
}
