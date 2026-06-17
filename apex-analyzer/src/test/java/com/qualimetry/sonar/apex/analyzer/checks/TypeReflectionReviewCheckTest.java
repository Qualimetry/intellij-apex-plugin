/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class TypeReflectionReviewCheckTest {

    private TypeReflectionReviewCheck check;

    @BeforeEach
    void setUp() { check = new TypeReflectionReviewCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-type-reflection-review"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-type-reflection-review", 3, 9); }
}
