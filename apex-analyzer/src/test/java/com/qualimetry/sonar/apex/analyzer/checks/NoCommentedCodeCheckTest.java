/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoCommentedCodeCheckTest {

    private NoCommentedCodeCheck check;

    @BeforeEach
    void setUp() { check = new NoCommentedCodeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-commented-code"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-commented-code", 10); }
}
