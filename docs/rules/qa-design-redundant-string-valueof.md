# Redundant String.valueOf() calls should be removed

`qa-design-redundant-string-valueof` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Calling String.valueOf() on an expression that is already a String is redundant. The call returns the same value without conversion, adding unnecessary method call overhead and visual noise.

## Noncompliant code example

```apex
public class Formatter {
    public String format(String input) {
        return String.valueOf(input); // Noncompliant - input is already a String
    }

    public void log(String message) {
        System.debug(String.valueOf(message)); // Noncompliant - message is already a String
    }
}
```

## Compliant solution

```apex
public class Formatter {
    public String format(String input) {
        return input;
    }

    public void log(String message) {
        System.debug(message);
    }
}
```
