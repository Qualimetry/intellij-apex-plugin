/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class CallSuperInConstructorCheckTest {
    private CallSuperInConstructorCheck check;

    @BeforeEach
    void setUp() { check = new CallSuperInConstructorCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-call-super-in-constructor"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-call-super-in-constructor", 4, 8); }
}
