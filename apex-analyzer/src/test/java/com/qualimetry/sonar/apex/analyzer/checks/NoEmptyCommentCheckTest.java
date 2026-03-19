/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoEmptyCommentCheckTest {

    private NoEmptyCommentCheck check;

    @BeforeEach
    void setUp() { check = new NoEmptyCommentCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-empty-comment"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-empty-comment", 1); }
}
