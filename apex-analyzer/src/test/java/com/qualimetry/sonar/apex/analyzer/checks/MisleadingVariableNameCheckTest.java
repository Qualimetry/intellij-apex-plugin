/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MisleadingVariableNameCheckTest {
    private MisleadingVariableNameCheck check;

    @BeforeEach
    void setUp() { check = new MisleadingVariableNameCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-misleading-variable-name"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-misleading-variable-name", 3, 7); }
}
