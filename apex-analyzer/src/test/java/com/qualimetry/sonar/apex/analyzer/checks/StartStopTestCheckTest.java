/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class StartStopTestCheckTest {

    private StartStopTestCheck check;

    @BeforeEach
    void setUp() { check = new StartStopTestCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-start-stop-test"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-start-stop-test", 12, 19); }
}
