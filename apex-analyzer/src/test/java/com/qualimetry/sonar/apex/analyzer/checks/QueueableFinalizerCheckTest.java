/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class QueueableFinalizerCheckTest {

    private QueueableFinalizerCheck check;

    @BeforeEach
    void setUp() { check = new QueueableFinalizerCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-bestpractice-queueable-finalizer"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-bestpractice-queueable-finalizer", 1); }
}
