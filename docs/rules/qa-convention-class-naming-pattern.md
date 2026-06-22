# Class names must conform to the naming pattern

`qa-convention-class-naming-pattern` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule enforces that class names conform to the configured naming pattern, typically PascalCase starting with an uppercase letter. Consistent naming improves codebase navigability and aligns with Apex community conventions. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class account_helper { // Noncompliant — not PascalCase
    public void processAccounts() {
        List<Account> accounts = [SELECT Id FROM Account LIMIT 10];
    }
}
```

## Compliant solution

```apex
public class AccountHelper {
    public void processAccounts() {
        List<Account> accounts = [SELECT Id FROM Account LIMIT 10];
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| format | Regular expression that class names must match | `^[A-Z][a-zA-Z0-9]*$` |
