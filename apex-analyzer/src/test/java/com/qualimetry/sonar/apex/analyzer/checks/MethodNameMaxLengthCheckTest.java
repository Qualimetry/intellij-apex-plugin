/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MethodNameMaxLengthCheckTest {
    private MethodNameMaxLengthCheck check;

    @BeforeEach
    void setUp() { check = new MethodNameMaxLengthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-method-name-max-length"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-method-name-max-length", 2); }
}
