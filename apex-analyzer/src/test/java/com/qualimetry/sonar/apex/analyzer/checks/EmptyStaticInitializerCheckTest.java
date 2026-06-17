/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EmptyStaticInitializerCheckTest {

    private EmptyStaticInitializerCheck check;

    @BeforeEach
    void setUp() { check = new EmptyStaticInitializerCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-empty-static-initializer"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-empty-static-initializer", 3, 13); }
}
