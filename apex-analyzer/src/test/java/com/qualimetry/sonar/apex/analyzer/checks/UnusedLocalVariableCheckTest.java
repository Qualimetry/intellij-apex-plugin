/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedLocalVariableCheckTest {

    private UnusedLocalVariableCheck check;

    @BeforeEach
    void setUp() { check = new UnusedLocalVariableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-local-variable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-local-variable", 5, 10); }
}
