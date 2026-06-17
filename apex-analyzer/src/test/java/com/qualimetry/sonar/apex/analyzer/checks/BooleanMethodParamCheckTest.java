/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class BooleanMethodParamCheckTest {

    private BooleanMethodParamCheck check;

    @BeforeEach
    void setUp() { check = new BooleanMethodParamCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-boolean-method-param"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-boolean-method-param", 8, 17); }
}
