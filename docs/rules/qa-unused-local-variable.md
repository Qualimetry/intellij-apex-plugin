# Local variables that are never read should be removed

`qa-unused-local-variable` &middot; Unused Code &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags local variables that are declared but never read. Unused variables clutter the code and may indicate incomplete refactoring or dead logic. Remove them to improve clarity. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountProcessor {
    public void process() {
        String status = 'Active'; // Noncompliant — never used
        List<Account> accounts = [SELECT Id FROM Account LIMIT 10];
        update accounts;
    }
}
```

## Compliant solution

```apex
public class AccountProcessor {
    public void process() {
        List<Account> accounts = [SELECT Id FROM Account LIMIT 10];
        update accounts;
    }
}
```
