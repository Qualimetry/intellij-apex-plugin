/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedPrivateFieldCheckTest {

    private UnusedPrivateFieldCheck check;

    @BeforeEach
    void setUp() { check = new UnusedPrivateFieldCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-private-field"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-private-field", 3, 4, 5); }
}
