/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class EventPublishLoopCheckTest {

    private EventPublishLoopCheck check;

    @BeforeEach
    void setUp() { check = new EventPublishLoopCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-event-publish-loop"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-event-publish-loop", 12); }
}
