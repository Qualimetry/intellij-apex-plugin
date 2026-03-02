# Qualimetry Apex Analyzer - IntelliJ Plugin

Static analysis of Salesforce Apex source files (`.cls`, `.trigger`) in IntelliJ IDEA, Rider, and other JetBrains IDEs. Also runs in JetBrains Qodana for CI/CD analysis.

Powered by the same analysis engine as the [Qualimetry Apex Analyzer for VS Code](https://marketplace.visualstudio.com/items?itemName=Qualimetry.qualimetry-vscode-apex-plugin) and the [Qualimetry Apex Analyzer for SonarQube](https://github.com/Qualimetry/sonarqube-apex-plugin).

## Features

- **280+ analysis rules** covering code quality, security, performance, and Salesforce best practices
- **Real-time analysis** as you edit Apex files in the IDE
- **Qodana-ready** — the same plugin runs in JetBrains Qodana for CI/CD analysis
- **Configurable rules** — enable/disable rules and override severities
- Recognises `.cls` and `.trigger` file extensions

## Supported IDEs

- **IntelliJ IDEA** 2024.3+ (Community or Ultimate)
- **Rider** 2024.3+
- Any other JetBrains IDE based on the IntelliJ Platform (WebStorm, GoLand, etc.)
- **JetBrains Qodana** (CI/CD)

## Requirements
- Java 17+ (for building from source only)

## Installation

### From JetBrains Marketplace

Search for **Qualimetry Apex Analyzer** in Settings > Plugins > Marketplace.

### From source

```bash
# The shared engine must be installed to Maven local first
cd <monorepo-root>
mvn clean install -pl apex-analyzer

# Then build the IntelliJ plugin
cd intellij-apex-plugin
./gradlew buildPlugin
```

The plugin ZIP is produced in `build/distributions/`.

## Configuration

Settings are under **Settings > Tools > Qualimetry Apex Analyzer**:

- **Enable/disable** the analyzer globally
- Per-rule overrides (enabled, severity) are stored in `qualimetry-apex.xml`

## Qodana

To use this plugin with Qodana, place the built plugin ZIP in `.qodana/` or mount it into the Qodana container, then enable the inspection in `qodana.yaml`:

```yaml
plugins:
  - id: com.qualimetry.apex
```

## License

Apache License 2.0
