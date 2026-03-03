/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class DefensiveArrayCopyCheckTest {

    private DefensiveArrayCopyCheck check;

    @BeforeEach
    void setUp() { check = new DefensiveArrayCopyCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-defensive-array-copy"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-defensive-array-copy", 6, 10); }
}
