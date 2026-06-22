/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class QueueableChainDepthCheckTest {

    private QueueableChainDepthCheck check;

    @BeforeEach
    void setUp() { check = new QueueableChainDepthCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-queueable-chain-depth"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-queueable-chain-depth", 10); }
}
