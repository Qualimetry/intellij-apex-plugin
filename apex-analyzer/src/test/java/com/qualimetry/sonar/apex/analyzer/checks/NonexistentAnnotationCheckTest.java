/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NonexistentAnnotationCheckTest {

    private NonexistentAnnotationCheck check;

    @BeforeEach
    void setUp() { check = new NonexistentAnnotationCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-nonexistent-annotation"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-nonexistent-annotation", 8, 14); }
}
