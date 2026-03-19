/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class LocaleInCaseConversionCheckTest {

    private LocaleInCaseConversionCheck check;

    @BeforeEach
    void setUp() { check = new LocaleInCaseConversionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-locale-in-case-conversion"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-locale-in-case-conversion", 3, 7, 18); }
}
