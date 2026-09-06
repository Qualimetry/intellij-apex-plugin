# Private methods that are never called should be removed

`qa-unused-private-method` &middot; Unused Code &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags private methods that are never called within the declaring class. Since private methods cannot be accessed externally, an unused private method is dead code that should be removed to reduce maintenance burden. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class ReportBuilder {
    public String buildReport(List<Account> accounts) {
        return formatHeader() + formatBody(accounts);
    }

    private String formatHeader() {
        return '--- Report ---\n';
    }

    private String formatBody(List<Account> accounts) {
        return String.join(accounts, ', ');
    }

    private String formatFooter() { // Noncompliant — never called
        return '--- End ---\n';
    }
}
```

## Compliant solution

```apex
public class ReportBuilder {
    public String buildReport(List<Account> accounts) {
        return formatHeader() + formatBody(accounts) + formatFooter();
    }

    private String formatHeader() {
        return '--- Report ---\n';
    }

    private String formatBody(List<Account> accounts) {
        return String.join(accounts, ', ');
    }

    private String formatFooter() {
        return '--- End ---\n';
    }
}
```
