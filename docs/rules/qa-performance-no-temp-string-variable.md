# Remove intermediate variables for string conversion

`qa-performance-no-temp-string-variable` &middot; Performance &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Creating a temporary variable solely to convert a value to a String before using it adds unnecessary heap allocation and clutters the code. Use String.valueOf() inline or rely on implicit string conversion where the Apex runtime supports it.

## Noncompliant code example

```apex
public class ReportGenerator {
    public String buildLine(Account acc) {
        String idStr = String.valueOf(acc.Id);           // Noncompliant — unnecessary temp
        String amountStr = String.valueOf(acc.AnnualRevenue);  // Noncompliant
        return idStr + ',' + amountStr;
    }
}
```

## Compliant solution

```apex
public class ReportGenerator {
    public String buildLine(Account acc) {
        return String.valueOf(acc.Id) + ',' + String.valueOf(acc.AnnualRevenue);
    }
}
```
