/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoStaticInInnerClassCheckTest {

    private NoStaticInInnerClassCheck check;

    @BeforeEach
    void setUp() { check = new NoStaticInInnerClassCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-static-in-inner-class"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-static-in-inner-class", 7, 8, 10); }
}
