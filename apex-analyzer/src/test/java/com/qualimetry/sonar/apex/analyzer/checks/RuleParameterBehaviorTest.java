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

import org.junit.jupiter.api.Test;

import static com.qualimetry.sonar.apex.analyzer.checks.CheckVerifier.runCheck;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies that the native rule parameters actually drive behaviour: a non-default value must
 * change which constructs the check flags. A parameter the check ignores fails these tests.
 */
class RuleParameterBehaviorTest {

    private static final String VARIABLES =
            "public class Sample {\n" +
            "    Integer a = 1;\n" +
            "    Integer name = 2;\n" +
            "}\n";

    @Test
    void variableMinLengthParameterChangesFlaggedIdentifiers() {
        VariableNameMinLengthCheck atDefault = new VariableNameMinLengthCheck();
        assertThat(runCheck(atDefault, VARIABLES))
                .as("default minLength=2 flags only the single-character name")
                .hasSize(1);

        VariableNameMinLengthCheck stricter = new VariableNameMinLengthCheck();
        stricter.setMinLength(5);
        assertThat(runCheck(stricter, VARIABLES))
                .as("minLength=5 also flags the four-character name")
                .hasSize(2);
    }

    private static final String COMPLEX_METHOD =
            "public class C {\n" +
            "    public void m() {\n" +
            "        if (a) { return; }\n" +
            "        if (b) { return; }\n" +
            "        if (c) { return; }\n" +
            "    }\n" +
            "}\n";

    @Test
    void cyclomaticThresholdParameterChangesFindings() {
        CyclomaticComplexityCheck atDefault = new CyclomaticComplexityCheck();
        assertThat(runCheck(atDefault, COMPLEX_METHOD))
                .as("default threshold=10 leaves a complexity-4 method clean")
                .isEmpty();

        CyclomaticComplexityCheck stricter = new CyclomaticComplexityCheck();
        stricter.setThreshold(2);
        assertThat(runCheck(stricter, COMPLEX_METHOD))
                .as("threshold=2 flags the same method")
                .hasSize(1);
    }

    private static final String CAMEL_CASE_VARIABLE =
            "public class P {\n" +
            "    public void m() {\n" +
            "        Integer myVar = 1;\n" +
            "    }\n" +
            "}\n";

    @Test
    void variableNamingPatternParameterChangesFindings() {
        VariableNamingPatternCheck atDefault = new VariableNamingPatternCheck();
        assertThat(runCheck(atDefault, CAMEL_CASE_VARIABLE))
                .as("default camelCase pattern accepts 'myVar'")
                .isEmpty();

        VariableNamingPatternCheck requiresUpper = new VariableNamingPatternCheck();
        requiresUpper.setFormat("^[A-Z][a-zA-Z0-9]*$");
        assertThat(runCheck(requiresUpper, CAMEL_CASE_VARIABLE))
                .as("an uppercase-first pattern rejects 'myVar'")
                .hasSize(1);
    }
}
