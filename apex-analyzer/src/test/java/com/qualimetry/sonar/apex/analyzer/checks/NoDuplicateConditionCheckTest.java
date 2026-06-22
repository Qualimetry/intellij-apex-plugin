/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoDuplicateConditionCheckTest {

    private NoDuplicateConditionCheck check;

    @BeforeEach
    void setUp() { check = new NoDuplicateConditionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-duplicate-condition"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-duplicate-condition", 12); }
}
