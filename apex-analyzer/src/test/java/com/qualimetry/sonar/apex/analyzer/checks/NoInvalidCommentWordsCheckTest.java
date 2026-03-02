/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoInvalidCommentWordsCheckTest {
    private NoInvalidCommentWordsCheck check;

    @BeforeEach
    void setUp() { check = new NoInvalidCommentWordsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-invalid-comment-words"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-invalid-comment-words", 2, 5); }
}
