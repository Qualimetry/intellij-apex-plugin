/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ExceptionNamingCheckTest {

    private ExceptionNamingCheck check;

    @BeforeEach
    void setUp() { check = new ExceptionNamingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-exception-naming"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-exception-naming", 1); }
}
