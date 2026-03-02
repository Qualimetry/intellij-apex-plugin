/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class UncommentedEmptyConstructorCheckTest {

    private UncommentedEmptyConstructorCheck check;

    @BeforeEach
    void setUp() { check = new UncommentedEmptyConstructorCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-design-uncommented-empty-constructor"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-design-uncommented-empty-constructor", 2, 6, 12); }
}
