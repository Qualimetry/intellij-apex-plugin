# Method names must conform to naming pattern

`qa-convention-method-naming-pattern` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule enforces that method names conform to the configured naming pattern, typically camelCase starting with a lowercase letter. Consistent naming aligns with Apex conventions and improves readability. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountHelper {
    public void Process_Accounts() { // Noncompliant — should be camelCase
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
