# Do not use == null where .equals() is intended

`qa-error-handling-equals-null` &middot; Error Handling &middot; Code Smell &middot; severity BLOCKER &middot; enabled in the recommended profile

Using == null to compare object equality when .equals() was intended can lead to incorrect logic. Conversely, using .equals(null) always returns false and is misleading. Ensure the correct comparison operator is used for the intended semantics.

## Noncompliant code example

```apex
public class Comparator {
    public Boolean isMatch(String a, String b) {
        return a.equals(null); // Noncompliant - always returns false
    }

    public Boolean checkValue(Object obj) {
        return obj.equals(null); // Noncompliant - always returns false, NPE if obj is null
    }
}
```

## Compliant solution

```apex
public class Comparator {
    public Boolean isMatch(String a, String b) {
        return a == null;
    }

    public Boolean checkValue(Object obj) {
        return obj == null;
    }
}
```
