/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ClassNameMaxLengthCheckTest {
    private ClassNameMaxLengthCheck check;

    @BeforeEach
    void setUp() { check = new ClassNameMaxLengthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-class-name-max-length"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-class-name-max-length", 1); }
}
