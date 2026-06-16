/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class IterateTriggerContextCheckTest {

    private IterateTriggerContextCheck check;

    @BeforeEach
    void setUp() { check = new IterateTriggerContextCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-iterate-trigger-context", "compliant.trigger"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-iterate-trigger-context", "noncompliant.trigger", 2, 4); }
}
