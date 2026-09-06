# Place literal values on the left side of comparisons

`qa-design-literals-first-in-comparison` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Placing the literal value on the left side of an equality comparison (known as a "Yoda condition") prevents accidental assignment when == is mistyped as =. It also makes the intent clearer when reading conditions that involve null checks.

## Noncompliant code example

```apex
public class AccountFilter {
    public Boolean isActive(Account acc) {
        if (acc.Status == 'Active') { // Noncompliant - literal on right
            return true;
        }
        if (acc.Type == 'Customer') { // Noncompliant - literal on right
            return true;
        }
        return false;
    }
}
```

## Compliant solution

```apex
public class AccountFilter {
    public Boolean isActive(Account acc) {
        if ('Active' == acc.Status) {
            return true;
        }
        if ('Customer' == acc.Type) {
            return true;
        }
        return false;
    }
}
```
