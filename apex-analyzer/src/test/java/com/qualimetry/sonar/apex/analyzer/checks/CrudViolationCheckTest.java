/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CrudViolationCheckTest {

    private CrudViolationCheck check;

    @BeforeEach
    void setUp() { check = new CrudViolationCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-crud-violation"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-crud-violation", 9, 14); }
}
