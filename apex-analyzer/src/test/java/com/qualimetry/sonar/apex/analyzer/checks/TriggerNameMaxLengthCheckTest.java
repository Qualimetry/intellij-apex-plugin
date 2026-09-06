/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class TriggerNameMaxLengthCheckTest {
    private TriggerNameMaxLengthCheck check;

    @BeforeEach
    void setUp() { check = new TriggerNameMaxLengthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-trigger-name-max-length"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-trigger-name-max-length", 1); }
}
