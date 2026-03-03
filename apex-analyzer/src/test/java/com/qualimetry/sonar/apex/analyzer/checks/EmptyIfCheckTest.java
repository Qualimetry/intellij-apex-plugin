/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptyIfCheckTest {

    private EmptyIfCheck check;

    @BeforeEach
    void setUp() { check = new EmptyIfCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-empty-if"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-empty-if", 3, 8, 16); }

}
