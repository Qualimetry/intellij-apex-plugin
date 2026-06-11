/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoFutureInLoopCheckTest {

    private NoFutureInLoopCheck check;

    @BeforeEach
    void setUp() { check = new NoFutureInLoopCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-no-future-in-loop"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-no-future-in-loop", 14); }
}
