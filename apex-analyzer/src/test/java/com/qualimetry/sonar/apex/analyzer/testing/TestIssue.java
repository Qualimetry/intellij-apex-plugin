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
package com.qualimetry.sonar.apex.analyzer.testing;

/**
 * Represents an issue detected by a check, used for evidence report
 * comparison between expected and actual results.
 *
 * @param line    the 1-based line number where the issue was detected
 * @param message the issue message, or {@code null} if not relevant
 */
public record TestIssue(int line, String message) {

    public TestIssue(int line) {
        this(line, null);
    }
}
