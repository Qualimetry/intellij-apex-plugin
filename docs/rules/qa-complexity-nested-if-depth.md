# If statements must not be nested beyond the allowed depth

`qa-complexity-nested-if-depth` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags if statements that are nested beyond the configured depth threshold. Deeply nested conditionals are difficult to read, test, and maintain. Refactor using early returns, guard clauses, or extracted methods to flatten the control flow. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class EligibilityChecker {
    public Boolean isEligible(Account a) {
        if (a != null) {
            if (a.IsActive__c) {
                if (a.AnnualRevenue > 1000) { // Noncompliant — depth 3
                    return true;
                }
            }
        }
        return false;
    }
}
```

## Compliant solution

```apex
public class EligibilityChecker {
    public Boolean isEligible(Account a) {
        if (a == null || !a.IsActive__c) {
            return false;
        }
        return a.AnnualRevenue > 1000;
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxDepth | Maximum allowed nesting depth for if statements | `3` |
