# Consecutive unary operators create confusing expressions

`qa-error-handling-no-multiple-unary` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Consecutive unary operators such as !!value or --count create confusing expressions that are hard to read and may not behave as the developer intended. Each negation or increment should be applied once with clear intent.

## Noncompliant code example

```apex
public class FlagChecker {
    public Boolean isEnabled(Boolean flag) {
        return !!flag; // Noncompliant - double negation
    }

    public Integer adjust(Integer count) {
        return --count; // Noncompliant - confusing double unary
    }
}
```

## Compliant solution

```apex
public class FlagChecker {
    public Boolean isEnabled(Boolean flag) {
        return flag;
    }

    public Integer adjust(Integer count) {
        count--;
        return count;
    }
}
```
