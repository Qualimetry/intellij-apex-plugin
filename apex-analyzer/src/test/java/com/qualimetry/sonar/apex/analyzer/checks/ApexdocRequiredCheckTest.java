/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ApexdocRequiredCheckTest {

    private ApexdocRequiredCheck check;

    @BeforeEach
    void setUp() { check = new ApexdocRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-documentation-apexdoc-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-documentation-apexdoc-required", 1); }
}
