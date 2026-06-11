/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class MergeCollapsibleIfCheckTest {

    private MergeCollapsibleIfCheck check;

    @BeforeEach
    void setUp() { check = new MergeCollapsibleIfCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-merge-collapsible-if"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-merge-collapsible-if", 4); }
}
