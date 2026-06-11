/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedDeclaredVariableCheckTest {

    private UnusedDeclaredVariableCheck check;

    @BeforeEach
    void setUp() { check = new UnusedDeclaredVariableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-declared-variable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-declared-variable", 10); }
}
