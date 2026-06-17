/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class LawOfDemeterCheckTest {

    private LawOfDemeterCheck check;

    @BeforeEach
    void setUp() { check = new LawOfDemeterCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-law-of-demeter"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-law-of-demeter", 3, 7); }
}
