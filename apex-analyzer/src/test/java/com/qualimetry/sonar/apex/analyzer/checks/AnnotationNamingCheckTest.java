/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AnnotationNamingCheckTest {

    private AnnotationNamingCheck check;

    @BeforeEach
    void setUp() { check = new AnnotationNamingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-annotation-naming"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-annotation-naming", 2, 7, 12); }
}
