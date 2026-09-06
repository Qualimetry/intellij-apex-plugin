# String literals must not be duplicated

`qa-convention-no-duplicate-literals` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags string literals that appear multiple times in the same class. Duplicated literals are error-prone during maintenance — changing one occurrence but missing another leads to inconsistent behavior. Extract duplicated strings into named constants. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class StatusService {
    public void activate(Account a) {
        a.Status__c = 'Active'; // Noncompliant — duplicated
        update a;
    }

    public Boolean isActive(Account a) {
        return a.Status__c == 'Active'; // Noncompliant — duplicated
    }
}
```

## Compliant solution

```apex
public class StatusService {
    private static final String STATUS_ACTIVE = 'Active';

    public void activate(Account a) {
        a.Status__c = STATUS_ACTIVE;
        update a;
    }

    public Boolean isActive(Account a) {
        return a.Status__c == STATUS_ACTIVE;
    }
}
```
