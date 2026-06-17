# Variable/parameter names must conform to pattern

`qa-convention-variable-naming-pattern` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule verifies that local variable and method parameter names conform to the configured naming pattern (default: camelCase starting with a lowercase letter). Consistent naming conventions improve readability and reduce confusion across the codebase. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountProcessor {
    public void process(List<Account> AccountList) { // Noncompliant — parameter starts uppercase
        Integer Total_Count = AccountList.size(); // Noncompliant — underscore in local variable
        for (Account A : AccountList) { // Noncompliant — single uppercase letter
            A.Description = 'Processed';
        }
    }
}
```

## Compliant solution

```apex
public class AccountProcessor {
    public void process(List<Account> accountList) {
        Integer totalCount = accountList.size();
        for (Account acct : accountList) {
            acct.Description = 'Processed';
        }
    }
}
```
