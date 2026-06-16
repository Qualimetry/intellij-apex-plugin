/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CouplingBetweenObjectsCheckTest {

    private CouplingBetweenObjectsCheck check;

    @BeforeEach
    void setUp() { check = new CouplingBetweenObjectsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-complexity-coupling-between-objects"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-complexity-coupling-between-objects", 1); }
}
