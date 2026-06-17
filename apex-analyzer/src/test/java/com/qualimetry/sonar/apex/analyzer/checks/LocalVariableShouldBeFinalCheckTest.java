/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class LocalVariableShouldBeFinalCheckTest {
    private LocalVariableShouldBeFinalCheck check;

    @BeforeEach
    void setUp() { check = new LocalVariableShouldBeFinalCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-local-variable-should-be-final"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-local-variable-should-be-final", 3); }
}
