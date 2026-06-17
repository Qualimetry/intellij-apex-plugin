/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnusedOperationOnImmutableCheckTest {

    private UnusedOperationOnImmutableCheck check;

    @BeforeEach
    void setUp() { check = new UnusedOperationOnImmutableCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-operation-on-immutable"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-operation-on-immutable", 3, 4, 5, 10); }
}
