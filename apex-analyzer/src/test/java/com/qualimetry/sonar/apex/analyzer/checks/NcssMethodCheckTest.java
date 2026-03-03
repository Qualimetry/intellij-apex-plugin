/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NcssMethodCheckTest {

    private NcssMethodCheck check;

    @BeforeEach
    void setUp() { check = new NcssMethodCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-ncss-method"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-ncss-method", 2); }
}
