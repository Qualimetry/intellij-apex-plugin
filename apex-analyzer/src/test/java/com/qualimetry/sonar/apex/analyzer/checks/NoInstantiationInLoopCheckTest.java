/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoInstantiationInLoopCheckTest {

    private NoInstantiationInLoopCheck check;

    @BeforeEach
    void setUp() { check = new NoInstantiationInLoopCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-no-instantiation-in-loop"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-no-instantiation-in-loop", 4, 13); }
}
