/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoRethrowCheckTest {

    private NoRethrowCheck check;

    @BeforeEach
    void setUp() { check = new NoRethrowCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-rethrow"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-rethrow", 6, 14); }
}
