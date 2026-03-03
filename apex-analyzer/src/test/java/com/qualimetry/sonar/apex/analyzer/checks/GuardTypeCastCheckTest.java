/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class GuardTypeCastCheckTest {

    private GuardTypeCastCheck check;

    @BeforeEach
    void setUp() { check = new GuardTypeCastCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-guard-type-cast"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-guard-type-cast", 3, 9); }
}
