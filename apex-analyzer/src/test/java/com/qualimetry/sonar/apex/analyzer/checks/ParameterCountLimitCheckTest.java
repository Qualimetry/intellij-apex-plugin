/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ParameterCountLimitCheckTest {

    private ParameterCountLimitCheck check;

    @BeforeEach
    void setUp() { check = new ParameterCountLimitCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-parameter-count-limit"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-parameter-count-limit", 4); }
}
