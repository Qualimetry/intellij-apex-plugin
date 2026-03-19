/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class PreferQueueableCheckTest {

    private PreferQueueableCheck check;

    @BeforeEach
    void setUp() { check = new PreferQueueableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-bestpractice-prefer-queueable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-bestpractice-prefer-queueable", 4, 13); }
}
