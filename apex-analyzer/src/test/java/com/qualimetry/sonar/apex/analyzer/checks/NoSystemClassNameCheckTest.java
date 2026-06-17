/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoSystemClassNameCheckTest {
    private NoSystemClassNameCheck check;

    @BeforeEach
    void setUp() { check = new NoSystemClassNameCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-system-class-name"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-system-class-name", 1, 8); }
}
