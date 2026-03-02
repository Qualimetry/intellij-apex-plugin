/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseIsblankCheckTest {

    private UseIsblankCheck check;

    @BeforeEach
    void setUp() { check = new UseIsblankCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-use-isblank"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-use-isblank", 3, 10); }
}
