/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoPublicClassFieldsCheckTest {

    private NoPublicClassFieldsCheck check;

    @BeforeEach
    void setUp() { check = new NoPublicClassFieldsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-security-no-public-class-fields"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-security-no-public-class-fields", 2, 3, 4); }
}
