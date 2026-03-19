/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ConstructorNoOverridableCallCheckTest {

    private ConstructorNoOverridableCallCheck check;

    @BeforeEach
    void setUp() { check = new ConstructorNoOverridableCallCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-constructor-no-overridable-call"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-constructor-no-overridable-call", 5, 6); }
}
