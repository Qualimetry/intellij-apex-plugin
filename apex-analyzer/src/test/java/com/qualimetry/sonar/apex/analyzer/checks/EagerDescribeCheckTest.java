/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EagerDescribeCheckTest {

    private EagerDescribeCheck check;

    @BeforeEach
    void setUp() { check = new EagerDescribeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-eager-describe"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-eager-describe", 9); }
}
