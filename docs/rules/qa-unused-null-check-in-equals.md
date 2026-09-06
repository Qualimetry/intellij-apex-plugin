# Remove unreachable null checks in equals

`qa-unused-null-check-in-equals` &middot; Unused Code &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Null checks inside equals() methods that are unreachable — because the framework or calling code already guarantees non-null arguments — are dead code. They clutter the method and give a false impression that null handling is necessary. Remove them or verify that they actually guard against a reachable null path.

## Noncompliant code example

```apex
public class ValueWrapper {
    private String value;

    public Boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ValueWrapper)) return false;
        ValueWrapper vw = (ValueWrapper) other;
        if (vw.value == null) return false;        // Noncompliant — unreachable if value is always set
        return this.value == vw.value;
    }
}
```

## Compliant solution

```apex
public class ValueWrapper {
    private String value;

    public Boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ValueWrapper)) return false;
        ValueWrapper vw = (ValueWrapper) other;
        return this.value == vw.value;
    }
}
```
