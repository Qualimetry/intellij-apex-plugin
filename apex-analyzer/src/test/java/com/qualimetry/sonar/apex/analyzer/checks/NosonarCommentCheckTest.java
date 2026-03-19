/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NosonarCommentCheckTest {

    private NosonarCommentCheck check;

    @BeforeEach
    void setUp() { check = new NosonarCommentCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-unused-nosonar-comment"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-unused-nosonar-comment", 3, 8); }
}
