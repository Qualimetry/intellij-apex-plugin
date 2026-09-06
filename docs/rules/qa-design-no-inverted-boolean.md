# Boolean expressions should not be unnecessarily inverted

`qa-design-no-inverted-boolean` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags boolean expressions that are unnecessarily inverted, such as !(a == b) instead of a != b. Removing the inversion simplifies the expression and improves readability. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class StatusChecker {
    public Boolean isNotActive(Account a) {
        return !(a.IsActive__c == true); // Noncompliant
    }
}
```

## Compliant solution

```apex
public class StatusChecker {
    public Boolean isNotActive(Account a) {
        return a.IsActive__c != true;
    }
}
```
