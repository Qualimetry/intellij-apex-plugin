/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseNamedCredentialsCheckTest {

    private UseNamedCredentialsCheck check;

    @BeforeEach
    void setUp() { check = new UseNamedCredentialsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-use-named-credentials"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-use-named-credentials", 4, 12); }
}
