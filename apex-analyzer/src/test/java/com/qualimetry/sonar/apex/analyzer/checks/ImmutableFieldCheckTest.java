/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ImmutableFieldCheckTest {

    private ImmutableFieldCheck check;

    @BeforeEach
    void setUp() { check = new ImmutableFieldCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-immutable-field"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-immutable-field", 2, 3); }
}
