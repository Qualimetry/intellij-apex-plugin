# Methods not using instance state should be static

`qa-design-method-should-be-static` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A method that does not reference any instance fields or use this does not depend on object state and should be declared static. Static methods are easier to test in isolation, can be called without instantiation, and clearly signal that they operate only on their parameters.

## Noncompliant code example

```apex
public class MathHelper {
    private Integer unusedField;

    public Integer add(Integer a, Integer b) { // Noncompliant - does not use instance state
        return a + b;
    }

    public String formatCurrency(Decimal amount) { // Noncompliant - does not use instance state
        return '$' + String.valueOf(amount);
    }
}
```

## Compliant solution

```apex
public class MathHelper {
    private Integer unusedField;

    public static Integer add(Integer a, Integer b) {
        return a + b;
    }

    public static String formatCurrency(Decimal amount) {
        return '$' + String.valueOf(amount);
    }
}
```
