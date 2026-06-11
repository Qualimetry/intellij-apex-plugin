/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EqualsWithoutHashcodeCheckTest {

    private EqualsWithoutHashcodeCheck check;

    @BeforeEach
    void setUp() { check = new EqualsWithoutHashcodeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-equals-without-hashcode"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-equals-without-hashcode", 1); }

    @Test
    void nonCompliantHashCodeOnly() {
        verifyNonCompliant(check, "qa-error-equals-without-hashcode", "noncompliant-hashcode-only.cls", 1);
    }
}
