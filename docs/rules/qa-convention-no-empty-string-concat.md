# Avoid concatenating empty strings for type conversion

`qa-convention-no-empty-string-concat` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags expressions that concatenate an empty string to convert a value to String (e.g. '' + someValue). This idiom is unclear and should be replaced with String.valueOf() for explicit, readable type conversion. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class InvoiceFormatter {
    public String formatAmount(Decimal amount) {
        String amountStr = '' + amount; // Noncompliant
        return '$' + amountStr;
    }

    public String formatCount(Integer count) {
        return 'Total: ' + '' + count; // Noncompliant
    }
}
```

## Compliant solution

```apex
public class InvoiceFormatter {
    public String formatAmount(Decimal amount) {
        String amountStr = String.valueOf(amount);
        return '$' + amountStr;
    }

    public String formatCount(Integer count) {
        return 'Total: ' + String.valueOf(count);
    }
}
```
