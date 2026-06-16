/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CacheableWireCheckTest {

    private CacheableWireCheck check;

    @BeforeEach
    void setUp() { check = new CacheableWireCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-cacheable-wire"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-cacheable-wire", 2, 7); }
}
