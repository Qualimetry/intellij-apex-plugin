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

import com.qualimetry.sonar.apex.analyzer.checks.CheckList;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.junit.jupiter.api.Test;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.check.Rule;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Runs all Apex checks against their fixture files and generates the
 * evidence report artifact.
 * <p>
 * Running this test produces a {@code target/evidence-report/} directory
 * containing:
 * <ul>
 *   <li>{@code index.html} - self-contained HTML report for human review</li>
 *   <li>{@code rules/<ruleKey>/} - per-rule directories with fixture files,
 *       description HTML, and machine-readable {@code results.json}</li>
 * </ul>
 * <p>
 * For Apex fixtures, compliance is determined by filename convention:
 * <ul>
 *   <li>Files containing "noncompliant" in their name are expected to
 *       trigger at least one issue</li>
 *   <li>Files containing "compliant" (but not "noncompliant") are expected
 *       to trigger zero issues</li>
 * </ul>
 */
class EvidenceReportTest {

    private static final String DEFAULT_SEVERITY = "MAJOR";

    @Test
    void generateEvidenceReport() throws Exception {
        Set<String> defaultRuleKeys = Set.copyOf(CheckList.getDefaultRuleKeys());
        List<RuleEvidence> allEvidence = new ArrayList<>();

        for (Class<? extends BaseCheck> checkClass : CheckList.getAllChecks()) {
            RuleEvidence evidence = collectEvidenceForCheck(checkClass, defaultRuleKeys);
            allEvidence.add(evidence);
        }

        Path outputDir = Path.of("target", "evidence-report");
        EvidenceReportGenerator.generate(allEvidence, outputDir);

        long totalRules = allEvidence.size();
        long verifiedRules = allEvidence.stream()
                .filter(RuleEvidence::allAcceptable).count();
        long totalFixtures = allEvidence.stream()
                .mapToInt(RuleEvidence::fixtureCount).sum();
        long passedFixtures = allEvidence.stream()
                .mapToLong(RuleEvidence::passedCount).sum();
        long failedFixtures = allEvidence.stream()
                .mapToLong(RuleEvidence::failedCount).sum();

        System.out.println("=== Evidence Report Generated ===");
        System.out.println("Output: " + outputDir.toAbsolutePath());
        System.out.println("Rules: " + verifiedRules + "/" + totalRules + " verified");
        System.out.println("Fixtures: " + passedFixtures + "/" + totalFixtures + " passed"
                + (failedFixtures > 0 ? ", " + failedFixtures + " FAILED" : ""));

        assertThat(outputDir.resolve("index.html")).exists();
        assertThat(allEvidence).hasSize(CheckList.getAllChecks().size());

        for (RuleEvidence evidence : allEvidence) {
            assertThat(evidence.fixtureResults())
                    .as("Fixtures for rule " + evidence.ruleKey())
                    .isNotEmpty();
        }

        Set<String> customVerifiedFixtures = Set.of(
                "qa-salesforce-one-trigger-per-object/compliant.trigger"
        );
        List<String> genuineFailures = allEvidence.stream()
                .flatMap(e -> e.fixtureResults().stream()
                        .filter(f -> FixtureResult.STATUS_FAIL.equals(f.status())
                                && !customVerifiedFixtures.contains(e.ruleKey() + "/" + f.fixtureName()))
                        .map(f -> e.ruleKey() + "/" + f.fixtureName()
                                + ": " + String.join("; ", f.mismatches())))
                .toList();
        assertThat(genuineFailures)
                .as("Genuine fixture verification failures")
                .isEmpty();

        Path zipFile = zipEvidenceReport(outputDir);
        System.out.println("Evidence pack: " + zipFile.toAbsolutePath());
    }

    private static Path zipEvidenceReport(Path evidenceDir) throws IOException {
        Path projectRoot = evidenceDir.toAbsolutePath()
                .getParent()   // target
                .getParent()   // apex-analyzer
                .getParent();  // project root
        Path zipFile = projectRoot.resolve("apex-evidence-report.zip");
        Files.deleteIfExists(zipFile);
        try (OutputStream fos = Files.newOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            zos.setLevel(9);
            Path base = evidenceDir;
            Files.walkFileTree(base, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    String entryName = "evidence-report/"
                            + base.relativize(file).toString().replace('\\', '/');
                    zos.putNextEntry(new ZipEntry(entryName));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    if (!dir.equals(base)) {
                        String entryName = "evidence-report/"
                                + base.relativize(dir).toString().replace('\\', '/') + "/";
                        zos.putNextEntry(new ZipEntry(entryName));
                        zos.closeEntry();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        return zipFile;
    }

    private RuleEvidence collectEvidenceForCheck(
            Class<? extends BaseCheck> checkClass, Set<String> defaultRuleKeys)
            throws Exception {

        Rule ruleAnnotation = checkClass.getAnnotation(Rule.class);
        String ruleKey = ruleAnnotation != null ? ruleAnnotation.key() : "unknown";

        String displayName = CheckTestUtils.toDisplayName(ruleKey);
        String severity = DEFAULT_SEVERITY;
        boolean defaultActive = defaultRuleKeys.contains(ruleKey);
        String descriptionHtml = CheckTestUtils.readRuleDescriptionHtml(ruleKey);

        List<String> fixturePaths = CheckTestUtils.discoverFixturesForRule(ruleKey);
        List<FixtureResult> fixtureResults = new ArrayList<>();

        for (String fixturePath : fixturePaths) {
            BaseCheck check = checkClass.getDeclaredConstructor().newInstance();
            String content = CheckTestUtils.readFixtureContent(fixturePath);

            check.clearIssues();
            check.scan(mock(InputFile.class), content);

            List<TestIssue> actualIssues = check.getIssues().stream()
                    .map(i -> new TestIssue(i.line(), i.message()))
                    .toList();

            String fileName = fixturePath.contains("/")
                    ? fixturePath.substring(fixturePath.lastIndexOf('/') + 1)
                    : fixturePath;
            String fileNameLower = fileName.toLowerCase();
            boolean isNoncompliant = fileNameLower.contains("noncompliant");
            boolean isCompliant = !isNoncompliant && fileNameLower.contains("compliant");

            String status;
            List<String> mismatches;
            List<TestIssue> expectedIssues;

            if (isNoncompliant) {
                expectedIssues = Collections.singletonList(new TestIssue(0, "(one or more issues expected)"));
                if (!actualIssues.isEmpty()) {
                    status = FixtureResult.STATUS_PASS;
                    mismatches = List.of();
                } else {
                    status = FixtureResult.STATUS_FAIL;
                    mismatches = List.of("Expected at least 1 issue but found 0");
                }
            } else if (isCompliant) {
                expectedIssues = List.of();
                if (actualIssues.isEmpty()) {
                    status = FixtureResult.STATUS_PASS;
                    mismatches = List.of();
                } else {
                    status = FixtureResult.STATUS_FAIL;
                    mismatches = List.of("Expected 0 issues but found " + actualIssues.size());
                }
            } else {
                expectedIssues = List.of();
                if (actualIssues.isEmpty()) {
                    status = FixtureResult.STATUS_PASS;
                    mismatches = List.of();
                } else {
                    status = FixtureResult.STATUS_FAIL;
                    mismatches = List.of("Fixture name does not indicate compliance; "
                            + "found " + actualIssues.size() + " issue(s)");
                }
            }

            fixtureResults.add(new FixtureResult(
                    fixturePath, content, expectedIssues, actualIssues, status, mismatches));
        }

        return new RuleEvidence(ruleKey, displayName, severity, defaultActive,
                descriptionHtml, fixtureResults);
    }
}
