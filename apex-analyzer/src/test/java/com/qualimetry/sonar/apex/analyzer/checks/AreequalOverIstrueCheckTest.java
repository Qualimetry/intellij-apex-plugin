/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AreequalOverIstrueCheckTest {

    private AreequalOverIstrueCheck check;

    @BeforeEach
    void setUp() { check = new AreequalOverIstrueCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-areequal-over-istrue"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-areequal-over-istrue", 6, 11); }
}
