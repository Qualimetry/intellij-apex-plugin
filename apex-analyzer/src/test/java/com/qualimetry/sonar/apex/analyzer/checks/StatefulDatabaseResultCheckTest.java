/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class StatefulDatabaseResultCheckTest {

    private StatefulDatabaseResultCheck check;

    @BeforeEach
    void setUp() { check = new StatefulDatabaseResultCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-error-stateful-database-result"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-error-stateful-database-result", 2); }
}
