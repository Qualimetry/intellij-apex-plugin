/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoTriggerLogicCheckTest {

    private NoTriggerLogicCheck check;

    @BeforeEach
    void setUp() { check = new NoTriggerLogicCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-no-trigger-logic", "compliant.trigger"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-no-trigger-logic", "noncompliant.trigger", 2, 4, 7, 8, 11, 12); }
}
