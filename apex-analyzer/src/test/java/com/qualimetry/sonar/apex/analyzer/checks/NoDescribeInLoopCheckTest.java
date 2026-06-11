/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoDescribeInLoopCheckTest {

    private NoDescribeInLoopCheck check;

    @BeforeEach
    void setUp() { check = new NoDescribeInLoopCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-no-describe-in-loop"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-no-describe-in-loop", 4, 5, 7); }
}
