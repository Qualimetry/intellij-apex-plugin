/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CommentHardcodedValuesCheckTest {
    private CommentHardcodedValuesCheck check;

    @BeforeEach
    void setUp() { check = new CommentHardcodedValuesCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-comment-hardcoded-values"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-comment-hardcoded-values", 2); }
}
