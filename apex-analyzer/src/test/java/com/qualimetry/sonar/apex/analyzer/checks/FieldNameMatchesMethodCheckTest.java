/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class FieldNameMatchesMethodCheckTest {
    private FieldNameMatchesMethodCheck check;

    @BeforeEach
    void setUp() { check = new FieldNameMatchesMethodCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-field-name-matches-method"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-field-name-matches-method", 2, 3); }
}
