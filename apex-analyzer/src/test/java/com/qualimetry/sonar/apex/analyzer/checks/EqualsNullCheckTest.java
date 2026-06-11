/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EqualsNullCheckTest {

    private EqualsNullCheck check;

    @BeforeEach
    void setUp() { check = new EqualsNullCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-equals-null"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-equals-null", 3, 11, 18); }
}
