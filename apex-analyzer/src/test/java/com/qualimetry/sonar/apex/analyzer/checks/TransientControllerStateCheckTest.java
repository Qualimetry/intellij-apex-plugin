/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class TransientControllerStateCheckTest {

    private TransientControllerStateCheck check;

    @BeforeEach
    void setUp() { check = new TransientControllerStateCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-transient-controller-state"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-transient-controller-state", 2, 3, 4, 5); }
}
