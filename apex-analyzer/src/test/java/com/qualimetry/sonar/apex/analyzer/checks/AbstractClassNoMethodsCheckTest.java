/*
 * Copyright 2026 SHAZAM Analytics Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qualimetry.sonar.apex.analyzer.checks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyCompliant;
import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.verifyNonCompliant;

class AbstractClassNoMethodsCheckTest {

    private AbstractClassNoMethodsCheck check;

    @BeforeEach
    void setUp() {
        check = new AbstractClassNoMethodsCheck();
    }

    @Test
    void compliant() {
        verifyCompliant(check, "qa-design-abstract-class-no-methods");
    }

    @Test
    void nonCompliant() {
        verifyNonCompliant(check, "qa-design-abstract-class-no-methods", 1, 6);
    }
}
