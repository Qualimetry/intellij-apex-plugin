/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class PreferNullCoalescingCheckTest {

    private PreferNullCoalescingCheck check;

    @BeforeEach
    void setUp() { check = new PreferNullCoalescingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-prefer-null-coalescing"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-prefer-null-coalescing", 3, 14, 19); }
}
