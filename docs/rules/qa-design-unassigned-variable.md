# Variables must be assigned before use

`qa-design-unassigned-variable` &middot; Design &middot; Code Smell &middot; severity INFO &middot; enabled in the recommended profile

Variables that are declared but never assigned a value before being used will contain null, which typically leads to NullPointerException at runtime. Ensure all variables are initialized before use, either at declaration time or through a guaranteed assignment path before the first read.

## Noncompliant code example

```apex
public class Calculator {
    public Decimal compute(Boolean useDiscount) {
        Decimal total;                             // Noncompliant — never assigned
        if (useDiscount) {
            total = 90;
        }
        return total;                              // may be null if useDiscount is false
    }
}
```

## Compliant solution

```apex
public class Calculator {
    public Decimal compute(Boolean useDiscount) {
        Decimal total = 100;
        if (useDiscount) {
            total = 90;
        }
        return total;
    }
}
```
