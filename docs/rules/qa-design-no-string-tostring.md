# Calling toString() on a String is redundant

`qa-design-no-string-tostring` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Calling toString() on a variable that is already a String is redundant. It adds visual noise, suggests the developer may not understand the variable's type, and creates unnecessary method call overhead.

## Noncompliant code example

```apex
public class Logger {
    public void logMessage(String message) {
        System.debug(message.toString()); // Noncompliant - already a String
    }

    public String format(String prefix, String suffix) {
        return prefix.toString() + suffix.toString(); // Noncompliant - both already Strings
    }
}
```

## Compliant solution

```apex
public class Logger {
    public void logMessage(String message) {
        System.debug(message);
    }

    public String format(String prefix, String suffix) {
        return prefix + suffix;
    }
}
```
