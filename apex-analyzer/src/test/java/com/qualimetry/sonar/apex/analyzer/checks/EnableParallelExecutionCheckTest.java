/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EnableParallelExecutionCheckTest {

    private EnableParallelExecutionCheck check;

    @BeforeEach
    void setUp() { check = new EnableParallelExecutionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-testing-enable-parallel-execution"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-testing-enable-parallel-execution", 1); }
}
