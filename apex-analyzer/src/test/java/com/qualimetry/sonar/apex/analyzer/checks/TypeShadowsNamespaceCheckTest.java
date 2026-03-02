/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class TypeShadowsNamespaceCheckTest {

    private TypeShadowsNamespaceCheck check;

    @BeforeEach
    void setUp() { check = new TypeShadowsNamespaceCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-type-shadows-namespace"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-type-shadows-namespace", 1); }
}
