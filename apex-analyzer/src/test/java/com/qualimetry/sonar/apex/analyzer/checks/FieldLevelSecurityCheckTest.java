/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class FieldLevelSecurityCheckTest {

    private FieldLevelSecurityCheck check;

    @BeforeEach
    void setUp() { check = new FieldLevelSecurityCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-field-level-security"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-field-level-security", 3, 8, 9, 13); }
}
