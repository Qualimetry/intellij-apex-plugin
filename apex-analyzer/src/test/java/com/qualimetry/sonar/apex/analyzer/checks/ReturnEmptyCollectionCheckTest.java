/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class ReturnEmptyCollectionCheckTest {

    private ReturnEmptyCollectionCheck check;

    @BeforeEach
    void setUp() { check = new ReturnEmptyCollectionCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-return-empty-collection"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-return-empty-collection", 3, 7, 11); }
}
