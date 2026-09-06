# Code blocks must not be left empty

`qa-error-handling-no-empty-block` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags empty code blocks — if, else, for, while, or try/catch bodies that contain no statements. Empty blocks suggest incomplete implementation or accidentally deleted logic. Add the intended logic or remove the block entirely. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class OrderValidator {
    public void validate(Order o) {
        if (o.Amount > 0) {
            // Noncompliant — empty block
        }
        for (OrderItem item : o.OrderItems) {
            // Noncompliant — empty block
        }
    }
}
```

## Compliant solution

```apex
public class OrderValidator {
    public void validate(Order o) {
        if (o.Amount > 0) {
            o.Status = 'Valid';
        }
        for (OrderItem item : o.OrderItems) {
            item.Status = 'Checked';
        }
    }
}
```
