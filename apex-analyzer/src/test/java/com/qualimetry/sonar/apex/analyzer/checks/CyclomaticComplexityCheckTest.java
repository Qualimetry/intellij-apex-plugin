/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CyclomaticComplexityCheckTest {

    private CyclomaticComplexityCheck check;

    @BeforeEach
    void setUp() { check = new CyclomaticComplexityCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-cyclomatic"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-cyclomatic", 2); }
}
