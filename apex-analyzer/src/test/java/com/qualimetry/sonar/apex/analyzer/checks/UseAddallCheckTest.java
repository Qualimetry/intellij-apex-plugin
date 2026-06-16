/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseAddallCheckTest {

    private UseAddallCheck check;

    @BeforeEach
    void setUp() { check = new UseAddallCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-use-addall"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-use-addall", 4, 7); }
}
