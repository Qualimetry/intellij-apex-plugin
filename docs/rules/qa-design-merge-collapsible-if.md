# Consecutive if statements with no else should be merged

`qa-design-merge-collapsible-if` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags consecutive if statements that can be merged into a single condition using &&. Unnecessary nesting increases cognitive complexity without adding value. Combining the conditions produces cleaner, flatter code. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountFilter {
    public Boolean isHighValue(Account a) {
        if (a != null) { // Noncompliant — collapsible
            if (a.AnnualRevenue > 1000000) {
                return true;
            }
        }
        return false;
    }
}
```

## Compliant solution

```apex
public class AccountFilter {
    public Boolean isHighValue(Account a) {
        if (a != null && a.AnnualRevenue > 1000000) {
            return true;
        }
        return false;
    }
}
```
