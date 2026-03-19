/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SuppressWarningsCheckTest {

    private SuppressWarningsCheck check;

    @BeforeEach
    void setUp() { check = new SuppressWarningsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-suppress-warnings"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-suppress-warnings", 2, 10); }
}
