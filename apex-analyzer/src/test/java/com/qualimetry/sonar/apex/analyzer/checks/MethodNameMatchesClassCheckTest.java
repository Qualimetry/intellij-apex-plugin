/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MethodNameMatchesClassCheckTest {

    private MethodNameMatchesClassCheck check;

    @BeforeEach
    void setUp() { check = new MethodNameMatchesClassCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-method-name-matches-class"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-method-name-matches-class", 2, 10); }
}
