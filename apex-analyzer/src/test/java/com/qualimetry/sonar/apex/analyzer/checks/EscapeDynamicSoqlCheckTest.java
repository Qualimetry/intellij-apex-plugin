/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EscapeDynamicSoqlCheckTest {

    private EscapeDynamicSoqlCheck check;

    @BeforeEach
    void setUp() { check = new EscapeDynamicSoqlCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-escape-dynamic-soql"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-escape-dynamic-soql", 3, 9); }
}
