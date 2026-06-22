/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoTabCharactersCheckTest {

    private NoTabCharactersCheck check;

    @BeforeEach
    void setUp() { check = new NoTabCharactersCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-tab-characters"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-tab-characters", 2, 4, 10); }
}
