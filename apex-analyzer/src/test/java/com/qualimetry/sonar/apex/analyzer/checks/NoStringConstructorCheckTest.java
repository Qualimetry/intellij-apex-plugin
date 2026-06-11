/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoStringConstructorCheckTest {

    private NoStringConstructorCheck check;

    @BeforeEach
    void setUp() { check = new NoStringConstructorCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-no-string-constructor"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-no-string-constructor", 3, 7, 8, 13); }
}
