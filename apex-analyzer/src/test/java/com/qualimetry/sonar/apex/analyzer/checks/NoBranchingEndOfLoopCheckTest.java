/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoBranchingEndOfLoopCheckTest {

    private NoBranchingEndOfLoopCheck check;

    @BeforeEach
    void setUp() { check = new NoBranchingEndOfLoopCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-handling-no-branching-end-of-loop"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-handling-no-branching-end-of-loop", 4, 10, 17); }
}
