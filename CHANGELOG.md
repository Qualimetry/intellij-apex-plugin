# Changelog

## [Unreleased]

- (None.)

## [1.9.0] - 2026-03-02

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- All 283 rules available as IntelliJ inspections for Apex files.
- Compatible with JetBrains Qodana for CI/CD static analysis.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.

## [1.0.0] - 2026-03-01

First public release.

- IntelliJ inspections for Salesforce Apex source files (`.cls`, `.trigger`).
- Qodana-compatible for CI/CD static analysis pipelines.
