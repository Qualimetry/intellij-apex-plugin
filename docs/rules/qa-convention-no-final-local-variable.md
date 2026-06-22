# Local variables should not use the final modifier

`qa-convention-no-final-local-variable` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

In Apex, using the final modifier on local variables adds visual noise without providing significant safety benefits in a garbage-collected, single-threaded execution context. Unlike languages where final affects optimization or concurrency semantics, Apex gains no runtime advantage from final locals. Keeping the codebase free of unnecessary modifiers improves readability and consistency.

## Noncompliant code example

```apex
public class InvoiceCalculator {
    public Decimal calculateTotal(List<LineItem> items) {
        final Decimal taxRate = 0.08; // Noncompliant - unnecessary final on local
        final Decimal subtotal = 0; // Noncompliant - unnecessary final on local

        for (LineItem item : items) {
            subtotal += item.amount;
        }

        return subtotal * (1 + taxRate);
    }
}
```

## Compliant solution

```apex
public class InvoiceCalculator {
    public Decimal calculateTotal(List<LineItem> items) {
        Decimal taxRate = 0.08;
        Decimal subtotal = 0;

        for (LineItem item : items) {
            subtotal += item.amount;
        }

        return subtotal * (1 + taxRate);
    }
}
```
