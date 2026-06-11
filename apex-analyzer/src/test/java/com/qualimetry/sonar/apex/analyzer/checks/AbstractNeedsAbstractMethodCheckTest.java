/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AbstractNeedsAbstractMethodCheckTest {

    private AbstractNeedsAbstractMethodCheck check;

    @BeforeEach
    void setUp() { check = new AbstractNeedsAbstractMethodCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-abstract-needs-abstract-method"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-abstract-needs-abstract-method", 1); }
}
