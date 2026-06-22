# Sections of code must not be commented out

`qa-convention-no-commented-code` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule detects sections of code that have been commented out rather than deleted. Commented-out code clutters the source, confuses readers about the intended behavior, and creates maintenance burden. Use version control to preserve old code instead of leaving it commented in the source file. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class OrderService {
    public void processOrder(Order o) {
        o.Status = 'Processing';
        // o.Priority = 'High'; // Noncompliant — commented-out code
        // if (o.Amount > 1000) {
        //     o.Priority = 'Urgent';
        // }
        update o;
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void processOrder(Order o) {
        o.Status = 'Processing';
        update o;
    }
}
```
