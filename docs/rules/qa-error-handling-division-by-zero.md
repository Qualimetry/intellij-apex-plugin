# Guard against potential division by zero

`qa-error-handling-division-by-zero` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Division or modulo by zero causes a runtime exception that crashes the transaction. Before performing division, validate that the divisor is not zero. This is especially important when the divisor comes from user input, SOQL results, or external data.

## Noncompliant code example

```apex
public class MetricsCalculator {
    public Decimal getAverage(Decimal total, Integer count) {
        return total / count; // Noncompliant - count could be zero
    }

    public Integer getRemainder(Integer value, Integer divisor) {
        return Math.mod(value, divisor); // Noncompliant - divisor could be zero
    }
}
```

## Compliant solution

```apex
public class MetricsCalculator {
    public Decimal getAverage(Decimal total, Integer count) {
        if (count == 0) {
            return 0;
        }
        return total / count;
    }

    public Integer getRemainder(Integer value, Integer divisor) {
        if (divisor == 0) {
            return 0;
        }
        return Math.mod(value, divisor);
    }
}
```
