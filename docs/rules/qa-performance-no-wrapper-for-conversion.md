# Avoid wrapper objects for primitive conversions

`qa-performance-no-wrapper-for-conversion` &middot; Performance &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Creating wrapper objects (e.g., new Integer()) solely to perform type conversion wastes heap memory and adds unnecessary overhead. Use the appropriate static conversion methods such as Integer.valueOf(), Decimal.valueOf(), or String.valueOf() instead.

## Noncompliant code example

```apex
public class DataConverter {
    public Integer convertToInt(String value) {
        return new Integer(value);                    // Noncompliant — wrapper for conversion
    }

    public String convertToString(Integer value) {
        return new String(value);                     // Noncompliant
    }
}
```

## Compliant solution

```apex
public class DataConverter {
    public Integer convertToInt(String value) {
        return Integer.valueOf(value);
    }

    public String convertToString(Integer value) {
        return String.valueOf(value);
    }
}
```
