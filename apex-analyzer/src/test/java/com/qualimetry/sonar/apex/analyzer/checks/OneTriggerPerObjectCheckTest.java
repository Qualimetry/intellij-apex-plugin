/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class OneTriggerPerObjectCheckTest {

    private OneTriggerPerObjectCheck check;

    @BeforeEach
    void setUp() { check = new OneTriggerPerObjectCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-one-trigger-per-object", "compliant.cls"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-one-trigger-per-object", "noncompliant.trigger", 1); }
}
