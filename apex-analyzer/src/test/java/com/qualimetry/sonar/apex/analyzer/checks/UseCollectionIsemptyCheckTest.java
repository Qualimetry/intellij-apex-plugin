/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UseCollectionIsemptyCheckTest {

    private UseCollectionIsemptyCheck check;

    @BeforeEach
    void setUp() { check = new UseCollectionIsemptyCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-performance-use-collection-isempty"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-performance-use-collection-isempty", 3, 6); }
}
