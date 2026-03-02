/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class HttpTimeoutCheckTest {

    private HttpTimeoutCheck check;

    @BeforeEach
    void setUp() { check = new HttpTimeoutCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-http-timeout"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-http-timeout", 14); }
}
