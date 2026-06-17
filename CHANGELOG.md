# Changelog

## [Unreleased]

- (None.)

## [1.9.16] - 2026-06-18

- Configurable rules (naming length/density, complexity and size thresholds, naming patterns) now expose native SonarQube rule parameters - editable in the UI and synced to the IDE in connected mode - instead of static documentation.

## [1.9.15] - 2026-06-17

### Added

- Quick-fix link from each inspection to its full-text rule documentation.
- Full-text, per-rule documentation covering what each rule checks, why it matters, and how to fix it.

## [1.9.14] - 2026-06-16

- Updated for compatibility with the latest JetBrains IDE releases.

## [1.9.13] - 2026-06-12

- SonarQube rule import now applies rule parameters and severities, and reuses the saved connection token.

## [1.9.12] - 2026-06-11

- Rules now re-sync automatically from the last-used SonarQube server and quality profile on IDE startup (toggle in Settings > Tools).
- **Import from SonarQube** button added to the settings page.
- SonarQube tokens are stored securely in the IDE credential store.

## [1.9.11] - 2026-03-19

- Plugin ZIP is now signed for JetBrains Marketplace verification.

## [1.9.10] - 2026-03-19

- Version alignment with VS Code and SonarQube plugins.

## [1.9.9] - 2026-03-03

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- All 283 rules available as IntelliJ inspections for Apex files.
- Per-rule settings panel with enable/disable, severity override, and search filter under Settings > Tools.
- **Import from SonarQube** — fetch active rules from a SonarQube quality profile via Tools > Qualimetry Apex > Import Rules from SonarQube.
- Per-rule inspection options for Qodana profile configuration.
- Compatible with JetBrains Qodana for CI/CD static analysis.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.

## [1.9.8] - 2026-03-03

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- All 283 rules available as IntelliJ inspections for Apex files.
- Compatible with JetBrains Qodana for CI/CD static analysis.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.

## [1.9.7] - 2026-03-03

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- All 283 rules available as IntelliJ inspections for Apex files.
- Compatible with JetBrains Qodana for CI/CD static analysis.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.

## [1.9.6] - 2026-03-03

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- All 283 rules available as IntelliJ inspections for Apex files.
- Compatible with JetBrains Qodana for CI/CD static analysis.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.

## [1.9.5] - 2026-03-03

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- All 283 rules available as IntelliJ inspections for Apex files.
- Compatible with JetBrains Qodana for CI/CD static analysis.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.

## [1.9.4] - 2026-03-02

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
