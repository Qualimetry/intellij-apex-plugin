/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class PublicStaticMustBeFinalCheckTest {
    private PublicStaticMustBeFinalCheck check;

    @BeforeEach
    void setUp() { check = new PublicStaticMustBeFinalCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-public-static-must-be-final"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-public-static-must-be-final", 2, 3); }
}
