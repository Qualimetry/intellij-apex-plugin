# Binary operators must not have identical expressions on both sides

`qa-error-handling-identical-binary-operands` &middot; Error Handling &middot; Bug &middot; severity MAJOR &middot; optional

This rule flags binary operators where both sides are identical expressions (e.g. a == a, x - x). Such expressions always produce a constant result and typically indicate a copy-paste error where one operand should reference a different variable. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class Calculator {
    public Boolean isDuplicate(String a, String b) {
        return a == a; // Noncompliant — should be a == b
    }

    public Integer difference(Integer x, Integer y) {
        return x - x; // Noncompliant — always zero
    }
}
```

## Compliant solution

```apex
public class Calculator {
    public Boolean isDuplicate(String a, String b) {
        return a == b;
    }

    public Integer difference(Integer x, Integer y) {
        return x - y;
    }
}
```
