/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class RedundantStringValueofCheckTest {

    private RedundantStringValueofCheck check;

    @BeforeEach
    void setUp() { check = new RedundantStringValueofCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-redundant-string-valueof"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-redundant-string-valueof", 3, 12, 18); }
}
