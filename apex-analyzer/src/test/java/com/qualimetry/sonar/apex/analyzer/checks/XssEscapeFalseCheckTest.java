/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class XssEscapeFalseCheckTest {

    private XssEscapeFalseCheck check;

    @BeforeEach
    void setUp() { check = new XssEscapeFalseCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-xss-escape-false"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-xss-escape-false", 5, 8); }
}
