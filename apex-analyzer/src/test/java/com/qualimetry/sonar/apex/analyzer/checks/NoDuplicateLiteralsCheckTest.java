/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoDuplicateLiteralsCheckTest {

    private NoDuplicateLiteralsCheck check;

    @BeforeEach
    void setUp() { check = new NoDuplicateLiteralsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-duplicate-literals"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-duplicate-literals", 10); }
}
