/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class SingleMethodSingletonCheckTest {

    private SingleMethodSingletonCheck check;

    @BeforeEach
    void setUp() { check = new SingleMethodSingletonCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-single-method-singleton"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-single-method-singleton", 1); }
}
