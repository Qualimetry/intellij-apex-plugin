/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class BooleanGetterNamingCheckTest {
    private BooleanGetterNamingCheck check;

    @BeforeEach
    void setUp() { check = new BooleanGetterNamingCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-boolean-getter-naming"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-boolean-getter-naming", 5, 9); }
}
