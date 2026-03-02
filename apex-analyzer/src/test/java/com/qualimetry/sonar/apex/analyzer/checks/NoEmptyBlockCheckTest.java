/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoEmptyBlockCheckTest {

    private NoEmptyBlockCheck check;

    @BeforeEach
    void setUp() { check = new NoEmptyBlockCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-empty-block"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-empty-block", 12); }
}
