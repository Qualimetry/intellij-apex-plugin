# Null checks before instanceof are redundant

`qa-design-unnecessary-null-check-instanceof` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A null check immediately before an instanceof test is redundant because instanceof already returns false when the operand is null. The extra null check adds unnecessary complexity without changing the behavior.

## Noncompliant code example

```apex
public class TypeChecker {
    public Boolean isAccount(Object obj) {
        if (obj != null && obj instanceof Account) { // Noncompliant - null check is redundant
            return true;
        }
        return false;
    }

    public Boolean isContact(Object obj) {
        return obj != null && obj instanceof Contact; // Noncompliant - null check is redundant
    }
}
```

## Compliant solution

```apex
public class TypeChecker {
    public Boolean isAccount(Object obj) {
        if (obj instanceof Account) {
            return true;
        }
        return false;
    }

    public Boolean isContact(Object obj) {
        return obj instanceof Contact;
    }
}
```
