/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoSeeAllDataCheckTest {

    private NoSeeAllDataCheck check;

    @BeforeEach
    void setUp() { check = new NoSeeAllDataCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-no-see-all-data"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-no-see-all-data", 3); }
}
