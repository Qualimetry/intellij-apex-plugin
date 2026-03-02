/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ConstructorMethodCallOrderCheckTest {

    private ConstructorMethodCallOrderCheck check;

    @BeforeEach
    void setUp() { check = new ConstructorMethodCallOrderCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-constructor-method-call-order"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-constructor-method-call-order", 6); }
}
