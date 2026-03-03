/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class FinalFieldShouldBeStaticCheckTest {
    private FinalFieldShouldBeStaticCheck check;

    @BeforeEach
    void setUp() { check = new FinalFieldShouldBeStaticCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-final-field-should-be-static"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-final-field-should-be-static", 2, 3); }
}
