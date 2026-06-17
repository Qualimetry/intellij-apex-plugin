/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class LiteralsFirstInComparisonCheckTest {

    private LiteralsFirstInComparisonCheck check;

    @BeforeEach
    void setUp() { check = new LiteralsFirstInComparisonCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-literals-first-in-comparison"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-literals-first-in-comparison", 3, 6, 9, 10); }
}
