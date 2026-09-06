# Remove parentheses that do not affect evaluation order

`qa-convention-no-useless-parens` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags parentheses in return statements and other expressions that do not affect evaluation order. Extra parentheses add visual noise and can obscure the intended logic of an expression. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class MathHelper {
    public Boolean isPositive(Integer n) {
        return (n > 0); // Noncompliant — parentheses are unnecessary
    }

    public Integer doubleValue(Integer n) {
        return (n * 2); // Noncompliant
    }
}
```

## Compliant solution

```apex
public class MathHelper {
    public Boolean isPositive(Integer n) {
        return n > 0;
    }

    public Integer doubleValue(Integer n) {
        return n * 2;
    }
}
```
