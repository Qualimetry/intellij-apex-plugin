/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptySwitchLabelCheckTest {

    private EmptySwitchLabelCheck check;

    @BeforeEach
    void setUp() { check = new EmptySwitchLabelCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-empty-switch-label"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-empty-switch-label", 4, 8); }
}
