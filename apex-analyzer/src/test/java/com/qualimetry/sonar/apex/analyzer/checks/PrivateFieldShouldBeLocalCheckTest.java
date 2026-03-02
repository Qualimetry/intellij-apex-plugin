/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class PrivateFieldShouldBeLocalCheckTest {

    private PrivateFieldShouldBeLocalCheck check;

    @BeforeEach
    void setUp() { check = new PrivateFieldShouldBeLocalCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-private-field-should-be-local"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-private-field-should-be-local", 2, 3); }
}
