/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseAssertClassCheckTest {

    private UseAssertClassCheck check;

    @BeforeEach
    void setUp() { check = new UseAssertClassCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-use-assert-class"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-use-assert-class", 5, 6, 7, 12, 13); }
}
