/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoHardcodedMessagesCheckTest {

    private NoHardcodedMessagesCheck check;

    @BeforeEach
    void setUp() { check = new NoHardcodedMessagesCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-hardcoded-messages"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-hardcoded-messages", 11); }
}
