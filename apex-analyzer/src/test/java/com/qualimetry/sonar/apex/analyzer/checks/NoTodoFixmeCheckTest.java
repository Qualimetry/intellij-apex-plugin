/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class NoTodoFixmeCheckTest {

    private NoTodoFixmeCheck check;

    @BeforeEach
    void setUp() { check = new NoTodoFixmeCheck(); }

    @Test
    void compliant() { verifyCompliant(check, "qa-convention-no-todo-fixme"); }

    @Test
    void nonCompliant() { verifyNonCompliant(check, "qa-convention-no-todo-fixme", 4, 14); }
}
