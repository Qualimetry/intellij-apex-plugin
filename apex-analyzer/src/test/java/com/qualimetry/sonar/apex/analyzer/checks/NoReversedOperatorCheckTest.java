/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoReversedOperatorCheckTest {

    private NoReversedOperatorCheck check;

    @BeforeEach
    void setUp() { check = new NoReversedOperatorCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-reversed-operator"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-reversed-operator", 6, 10, 11); }
}
