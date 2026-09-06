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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RuleDocsCoverageTest {

    @Test
    void everyRuleKeyHasADocFile() {
        File docsDir = locateDocsDir();
        assertThat(docsDir)
                .as("docs/rules directory must exist; run 'node scripts/generate-rule-docs.js'")
                .isDirectory();

        List<String> missing = new ArrayList<>();
        for (String ruleKey : CheckList.getAllRuleKeys()) {
            File doc = new File(docsDir, ruleKey + ".md");
            if (!doc.isFile()) {
                missing.add(ruleKey);
            }
        }

        assertThat(missing)
                .as("rule keys missing docs/rules/<key>.md; run 'node scripts/generate-rule-docs.js'")
                .isEmpty();
    }

    @Test
    void helpUrlPointsAtTheDocForTheRule() {
        String url = RuleHelpUrls.helpUrl("qa-apex-syntax");
        assertThat(url)
                .isEqualTo("https://github.com/Qualimetry/sonarqube-apex-plugin/blob/main/docs/rules/qa-apex-syntax.md");
    }

    private static File locateDocsDir() {
        File dir = new File("").getAbsoluteFile();
        while (dir != null) {
            File candidate = new File(dir, "docs/rules");
            if (candidate.isDirectory()) {
                return candidate;
            }
            dir = dir.getParentFile();
        }
        return new File("docs/rules");
    }
}
