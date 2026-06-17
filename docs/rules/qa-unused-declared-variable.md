# Declared local variables that are never used should be removed

`qa-unused-declared-variable` &middot; Unused Code &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags local variables that are declared but never read or used afterward. Unused variables waste memory, add visual noise, and may indicate an incomplete implementation or a copy-paste error. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountHelper {
    public void processAccounts(List<Account> accounts) {
        Integer counter = 0; // Noncompliant — counter is never read
        for (Account a : accounts) {
            a.Description = 'Processed';
        }
        update accounts;
    }
}
```

## Compliant solution

```apex
public class AccountHelper {
    public void processAccounts(List<Account> accounts) {
        for (Account a : accounts) {
            a.Description = 'Processed';
        }
        update accounts;
    }
}
```
