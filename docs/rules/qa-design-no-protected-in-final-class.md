# Final classes should not contain protected fields

`qa-design-no-protected-in-final-class` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

The protected access modifier is designed to allow subclass access, but a final class cannot be extended. Declaring protected members in a final class is misleading because no subclass will ever exist to use that access level. Use private for encapsulation or public if external access is genuinely needed.

## Noncompliant code example

```apex
public final class CurrencyConverter {
    protected Decimal exchangeRate; // Noncompliant - protected in final class
    protected String baseCurrency; // Noncompliant - protected in final class

    public Decimal convert(Decimal amount) {
        return amount * exchangeRate;
    }
}
```

## Compliant solution

```apex
public final class CurrencyConverter {
    private Decimal exchangeRate;
    private String baseCurrency;

    public CurrencyConverter(Decimal rate, String currency) {
        this.exchangeRate = rate;
        this.baseCurrency = currency;
    }

    public Decimal convert(Decimal amount) {
        return amount * exchangeRate;
    }
}
```
