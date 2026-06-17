# Redundant parentheses should be removed

`qa-convention-no-redundant-parentheses` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags redundant parentheses that do not affect evaluation order. Extra parentheses add visual noise and make expressions harder to read. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class Calculator {
    public Integer compute(Integer a, Integer b) {
        return ((a + b)); // Noncompliant — extra parentheses
    }
}
```

## Compliant solution

```apex
public class Calculator {
    public Integer compute(Integer a, Integer b) {
        return a + b;
    }
}
```
