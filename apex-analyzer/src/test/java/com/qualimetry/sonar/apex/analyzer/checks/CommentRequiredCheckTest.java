/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CommentRequiredCheckTest {
    private CommentRequiredCheck check;

    @BeforeEach
    void setUp() { check = new CommentRequiredCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-comment-required"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-comment-required", 2); }
}
