/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NonInstantiableMissingStaticCheckTest {

    private NonInstantiableMissingStaticCheck check;

    @BeforeEach
    void setUp() { check = new NonInstantiableMissingStaticCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-non-instantiable-missing-static"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-non-instantiable-missing-static", 2); }  // returns after first private ctor
}
