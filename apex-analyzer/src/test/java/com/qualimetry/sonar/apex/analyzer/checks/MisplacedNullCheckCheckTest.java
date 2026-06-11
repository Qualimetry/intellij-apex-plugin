/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MisplacedNullCheckCheckTest {

    private MisplacedNullCheckCheck check;

    @BeforeEach
    void setUp() { check = new MisplacedNullCheckCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-misplaced-null-check"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-misplaced-null-check", 3, 19); }
}
