/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class DebugLoggingLevelCheckTest {

    private DebugLoggingLevelCheck check;

    @BeforeEach
    void setUp() { check = new DebugLoggingLevelCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-bestpractice-debug-logging-level"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-bestpractice-debug-logging-level", 9, 12, 15); }
}
