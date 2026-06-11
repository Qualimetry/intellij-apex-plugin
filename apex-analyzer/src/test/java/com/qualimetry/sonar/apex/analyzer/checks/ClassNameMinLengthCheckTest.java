/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ClassNameMinLengthCheckTest {
    private ClassNameMinLengthCheck check;

    @BeforeEach
    void setUp() { check = new ClassNameMinLengthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-class-name-min-length"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-class-name-min-length", 1, 9); }
}
