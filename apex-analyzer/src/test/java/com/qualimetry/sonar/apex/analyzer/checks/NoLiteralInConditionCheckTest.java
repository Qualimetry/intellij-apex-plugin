/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoLiteralInConditionCheckTest {
    private NoLiteralInConditionCheck check;

    @BeforeEach
    void setUp() { check = new NoLiteralInConditionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-literal-in-condition"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-literal-in-condition", 4, 9); }
}
