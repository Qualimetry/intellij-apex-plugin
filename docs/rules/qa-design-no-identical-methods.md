# Methods must not have identical implementations

`qa-design-no-identical-methods` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags methods that have identical implementations. Duplicated method bodies increase maintenance cost and risk inconsistent updates. Extract the shared logic into a single method and have both callers invoke it. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountHelper {
    public void activateAccount(Account a) {
        a.Status__c = 'Active';
        a.LastModified__c = System.now();
        update a;
    }
    public void enableAccount(Account a) { // Noncompliant — identical
        a.Status__c = 'Active';
        a.LastModified__c = System.now();
        update a;
    }
}
```

## Compliant solution

```apex
public class AccountHelper {
    public void activateAccount(Account a) {
        setActive(a);
    }
    public void enableAccount(Account a) {
        setActive(a);
    }
    private void setActive(Account a) {
        a.Status__c = 'Active';
        a.LastModified__c = System.now();
        update a;
    }
}
```
