# Null checks must be correctly structured to prevent NPE

`qa-error-handling-broken-null-check` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

A null check that uses || instead of && (or vice versa) fails to protect against NullPointerException. For example, obj == null || obj.method() will still call obj.method() when obj is not null, but obj != null || obj.method() will dereference null when obj is null.

## Noncompliant code example

```apex
public class SafeAccess {
    public void process(Account acc) {
        if (acc != null || acc.Name.length() > 0) { // Noncompliant - should use &&
            System.debug(acc.Name);
        }
    }

    public Boolean isValid(String value) {
        return value != null || value.length() > 0; // Noncompliant - should use &&
    }
}
```

## Compliant solution

```apex
public class SafeAccess {
    public void process(Account acc) {
        if (acc != null && acc.Name.length() > 0) {
            System.debug(acc.Name);
        }
    }

    public Boolean isValid(String value) {
        return value != null && value.length() > 0;
    }
}
```
