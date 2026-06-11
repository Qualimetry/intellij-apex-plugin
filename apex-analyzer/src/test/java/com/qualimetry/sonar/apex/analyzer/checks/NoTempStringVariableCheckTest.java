/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoTempStringVariableCheckTest {

    private NoTempStringVariableCheck check;

    @BeforeEach
    void setUp() { check = new NoTempStringVariableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-no-temp-string-variable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-no-temp-string-variable", 3, 8); }
}
