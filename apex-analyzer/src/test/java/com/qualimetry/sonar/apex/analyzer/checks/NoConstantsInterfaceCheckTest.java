/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoConstantsInterfaceCheckTest {
    private NoConstantsInterfaceCheck check;

    @BeforeEach
    void setUp() { check = new NoConstantsInterfaceCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-constants-interface"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-constants-interface", 1); }
}
