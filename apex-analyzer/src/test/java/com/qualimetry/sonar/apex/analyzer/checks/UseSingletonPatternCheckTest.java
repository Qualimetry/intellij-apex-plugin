/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseSingletonPatternCheckTest {

    private UseSingletonPatternCheck check;

    @BeforeEach
    void setUp() { check = new UseSingletonPatternCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-use-singleton-pattern"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-use-singleton-pattern", 1); }
}
