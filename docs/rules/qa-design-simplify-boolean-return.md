# Return must not use redundant boolean literals

`qa-design-simplify-boolean-return` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags return statements that use redundant boolean literals in ternary or if/else patterns, such as return x ? true : false. The condition itself is the boolean value and should be returned directly. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class EligibilityChecker {
    public Boolean isEligible(Account a) {
        if (a.AnnualRevenue > 1000) { // Noncompliant
            return true;
        } else {
            return false;
        }
    }
    public Boolean isActive(Account a) {
        return a.IsActive__c ? true : false; // Noncompliant
    }
}
```

## Compliant solution

```apex
public class EligibilityChecker {
    public Boolean isEligible(Account a) {
        return a.AnnualRevenue > 1000;
    }
    public Boolean isActive(Account a) {
        return a.IsActive__c;
    }
}
```
