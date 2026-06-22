# Use isEmpty() or isBlank() instead of length-based empty checks

`qa-performance-efficient-empty-check` &middot; Performance &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Checking if a string is empty by comparing its length to zero (str.length() == 0) is less readable and more error-prone than using String.isEmpty() or String.isBlank(). The dedicated methods also handle null values safely, preventing NullPointerException.

## Noncompliant code example

```apex
public class InputValidator {
    public Boolean isValid(String input) {
        if (input != null && input.length() == 0) {     // Noncompliant
            return false;
        }
        if (input != null && input.trim().length() == 0) {  // Noncompliant
            return false;
        }
        return true;
    }
}
```

## Compliant solution

```apex
public class InputValidator {
    public Boolean isValid(String input) {
        if (String.isEmpty(input)) {
            return false;
        }
        if (String.isBlank(input)) {
            return false;
        }
        return true;
    }
}
```
