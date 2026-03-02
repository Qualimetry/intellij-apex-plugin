/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ExtendExceptionClassCheckTest {

    private ExtendExceptionClassCheck check;

    @BeforeEach
    void setUp() { check = new ExtendExceptionClassCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-extend-exception-class"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-extend-exception-class", 2, 6); }
}
