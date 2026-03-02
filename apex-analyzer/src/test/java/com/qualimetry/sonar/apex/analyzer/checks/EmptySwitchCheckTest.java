/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptySwitchCheckTest {

    private EmptySwitchCheck check;

    @BeforeEach
    void setUp() { check = new EmptySwitchCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-empty-switch"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-empty-switch", 3, 15); }
}
