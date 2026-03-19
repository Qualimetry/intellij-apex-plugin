/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AuraenabledErrorHandlingCheckTest {

    private AuraenabledErrorHandlingCheck check;

    @BeforeEach
    void setUp() { check = new AuraenabledErrorHandlingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-auraenabled-error-handling"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-auraenabled-error-handling", 2, 7, 12); }
}
