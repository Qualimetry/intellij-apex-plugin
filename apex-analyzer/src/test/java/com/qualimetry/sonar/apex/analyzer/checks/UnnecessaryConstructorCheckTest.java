/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UnnecessaryConstructorCheckTest {

    private UnnecessaryConstructorCheck check;

    @BeforeEach
    void setUp() { check = new UnnecessaryConstructorCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-unnecessary-constructor"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-unnecessary-constructor", 5); }
}
