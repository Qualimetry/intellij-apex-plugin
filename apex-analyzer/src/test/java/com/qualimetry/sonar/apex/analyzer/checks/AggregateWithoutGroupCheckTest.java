/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AggregateWithoutGroupCheckTest {

    private AggregateWithoutGroupCheck check;

    @BeforeEach
    void setUp() { check = new AggregateWithoutGroupCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-salesforce-aggregate-without-group"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-salesforce-aggregate-without-group", 3, 8, 13); }
}
