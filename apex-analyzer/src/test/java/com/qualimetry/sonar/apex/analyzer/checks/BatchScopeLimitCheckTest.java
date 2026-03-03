/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class BatchScopeLimitCheckTest {

    private BatchScopeLimitCheck check;

    @BeforeEach
    void setUp() { check = new BatchScopeLimitCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-batch-scope-limit"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-batch-scope-limit", 5); }
}
