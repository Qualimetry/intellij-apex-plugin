/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoDmlSoqlInLoopCheckTest {

    private NoDmlSoqlInLoopCheck check;

    @BeforeEach
    void setUp() { check = new NoDmlSoqlInLoopCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-no-dml-soql-in-loop"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-no-dml-soql-in-loop", 11, 15); }
}
