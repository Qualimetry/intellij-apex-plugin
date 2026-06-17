/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseAssertEqualsCheckTest {

    private UseAssertEqualsCheck check;

    @BeforeEach
    void setUp() { check = new UseAssertEqualsCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-use-assert-equals"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-use-assert-equals", 6, 12); }
}
