# Qualimetry Apex Analyzer - IntelliJ Plugin

Static analysis of Salesforce Apex source files (`.cls`, `.trigger`) in IntelliJ IDEA, Rider, and other JetBrains IDEs, with CI/CD support via JetBrains Qodana.

Powered by the same analysis engine as the [Qualimetry Apex Analyzer for VS Code](https://github.com/Qualimetry/vscode-apex-plugin) and the [Qualimetry Apex Analyzer for SonarQube](https://github.com/Qualimetry/sonarqube-apex-plugin).

## Features

- **283 rules** covering convention, naming, design, security, error handling, performance, testing, complexity, and Salesforce platform best practices.
- **Real-time analysis** as you edit Apex files in the IDE.
- **Qodana-ready** — the same plugin runs in JetBrains Qodana for CI/CD analysis.
- **Configurable rules** — enable/disable rules and override severities via a per-rule settings panel.
- **SonarQube import** — import active rules from a SonarQube quality profile via the IDE's main menu bar (**Tools > Qualimetry Apex > Import Rules from SonarQube**) or the **Import from SonarQube...** button on the settings page.

## Rule categories

| Category | Examples |
|----------|----------|
| Convention | Class/method/variable naming, braces, indentation |
| Design | Boolean parameters, law of demeter, collapsible if, ternary |
| Security | SOQL injection, field-level security, XSS, CSRF |
| Error Handling | Empty catch, generic catch, preserve stack trace |
| Performance | SOQL/DML in loops, eager describe, wrapper conversion |
| Complexity | Cyclomatic, cognitive, NCSS, coupling, god class |
| Testing | Assertions required, no SeeAllData, test method coverage |
| Salesforce | One trigger per object, no trigger logic, API versions |
| Unused Code | Unused variables, parameters, private fields/methods |
| Documentation | Apexdoc required, comment density |

## Installation

### From JetBrains Marketplace

Search for **Qualimetry Apex Analyzer** in Settings > Plugins > Marketplace.

### From source

```bash
# The shared engine must be installed to Maven local first
cd <monorepo-root>
mvn clean install -pl apex-analyzer

# Then build the IntelliJ plugin
cd intellij-plugin
./gradlew buildPlugin
```

The plugin ZIP is produced in `build/distributions/`.

## Configuration

Settings are under **Settings > Tools > Qualimetry Apex Analyzer**:

- **Enable/disable** the analyzer globally
- **Per-rule table** — enable/disable individual rules, set severity overrides, filter by name or key
- **Reset to Defaults** — clear all overrides and return to the default profile
- Per-rule overrides are stored in `qualimetry-apex.xml`

### Import from SonarQube

To mirror a SonarQube quality profile, use either:

- The IDE's **main menu bar**: **Tools > Qualimetry Apex > Import Rules from SonarQube**. This is the Tools menu at the top of the IDE window — not the Settings > Tools section. In Rider's new UI, the menu bar is behind the hamburger icon.
- The **Import from SonarQube...** button on the settings page (**Settings > Tools > Qualimetry Apex Analyzer**).

Enter your server URL, an optional authentication token, and an optional profile name. The imported rules replace the current configuration. The server URL and profile name are remembered between sessions, and the token (if provided) is stored securely in the IDE's credential store.

After a first successful import, the plugin automatically re-syncs rules from the same server and profile each time the IDE starts, so profile changes in SonarQube propagate without a manual re-import. To turn this off, untick **Automatically sync rules from SonarQube on startup** on the settings page.

## Supported IDEs

- **IntelliJ IDEA** 2024.3+ (Community or Ultimate)
- **Rider** 2024.3+
- Any other JetBrains IDE based on the IntelliJ Platform (WebStorm, GoLand, etc.)
- **JetBrains Qodana** (CI/CD)

## Requirements

- Java 17+ (for building from source only)

## Qodana

To use this plugin with Qodana, place the built plugin ZIP in `.qodana/` or mount it into the Qodana container, then enable the inspection in `qodana.yaml`:

```yaml
plugins:
  - id: com.qualimetry.apex
```

## Also available

The same analysis engine powers plugins for other platforms:

- **[VS Code extension](https://github.com/Qualimetry/vscode-apex-plugin)** — catch issues as you type in VS Code.
- **[SonarQube plugin](https://github.com/Qualimetry/sonarqube-apex-plugin)** — enforce quality gates in CI/CD pipelines.

Rule keys and severities align across all three tools so findings are directly comparable.

## License

Apache License 2.0
