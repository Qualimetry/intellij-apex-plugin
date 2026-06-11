/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ParserFailureCheckTest {

    private ParserFailureCheck check;

    @BeforeEach
    void setUp() { check = new ParserFailureCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-parser-failure"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-parser-failure", 1); }
}
