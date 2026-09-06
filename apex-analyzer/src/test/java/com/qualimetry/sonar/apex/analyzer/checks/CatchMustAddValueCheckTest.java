/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CatchMustAddValueCheckTest {

    private CatchMustAddValueCheck check;

    @BeforeEach
    void setUp() { check = new CatchMustAddValueCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-catch-must-add-value"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-catch-must-add-value", 6, 14); }
}
