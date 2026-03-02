/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class RelativeUrlCheckTest {

    private RelativeUrlCheck check;

    @BeforeEach
    void setUp() { check = new RelativeUrlCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-relative-url"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-relative-url", 14); }
}
