/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoIsrunningtestCheckTest {

    private NoIsrunningtestCheck check;

    @BeforeEach
    void setUp() { check = new NoIsrunningtestCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-no-isrunningtest"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-no-isrunningtest", 3, 13); }
}
