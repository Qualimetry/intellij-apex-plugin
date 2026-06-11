/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class IstrueOverAreequalCheckTest {

    private IstrueOverAreequalCheck check;

    @BeforeEach
    void setUp() { check = new IstrueOverAreequalCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-istrue-over-areequal"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-istrue-over-areequal", 5, 10); }
}
