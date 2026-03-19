/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CognitiveComplexityCheckTest {

    private CognitiveComplexityCheck check;

    @BeforeEach
    void setUp() { check = new CognitiveComplexityCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-cognitive"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-cognitive", 2); }
}
