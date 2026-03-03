/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class VariableNameMaxLengthCheckTest {
    private VariableNameMaxLengthCheck check;

    @BeforeEach
    void setUp() { check = new VariableNameMaxLengthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-variable-name-max-length"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-variable-name-max-length", 3); }
}
