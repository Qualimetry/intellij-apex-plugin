/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ForLoopShouldBeWhileCheckTest {

    private ForLoopShouldBeWhileCheck check;

    @BeforeEach
    void setUp() { check = new ForLoopShouldBeWhileCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-for-loop-should-be-while"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-for-loop-should-be-while", 4, 10); }
}
