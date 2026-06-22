/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoBatchFromTriggerCheckTest {

    private NoBatchFromTriggerCheck check;

    @BeforeEach
    void setUp() { check = new NoBatchFromTriggerCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-no-batch-from-trigger", "compliant.trigger"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-no-batch-from-trigger", "noncompliant.trigger", 2, 5); }
}
