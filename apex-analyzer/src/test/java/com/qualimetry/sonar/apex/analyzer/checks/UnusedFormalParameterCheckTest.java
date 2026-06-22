/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedFormalParameterCheckTest {

    private UnusedFormalParameterCheck check;

    @BeforeEach
    void setUp() { check = new UnusedFormalParameterCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-formal-parameter"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-formal-parameter", 9); }
}
