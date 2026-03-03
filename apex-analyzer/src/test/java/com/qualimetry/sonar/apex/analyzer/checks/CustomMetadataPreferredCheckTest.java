/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CustomMetadataPreferredCheckTest {

    private CustomMetadataPreferredCheck check;

    @BeforeEach
    void setUp() { check = new CustomMetadataPreferredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-custom-metadata-preferred"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-custom-metadata-preferred", 9); }
}
