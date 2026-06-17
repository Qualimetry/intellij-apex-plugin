# Use String.isBlank() for null/empty validation

`qa-performance-use-isblank` &middot; Performance &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Manually checking for null and empty strings (str != null && str != '') is verbose and error-prone. String.isBlank() handles null, empty, and whitespace-only strings in a single call, while String.isNotBlank() provides the inverse check. Using these methods improves readability and eliminates common null-check mistakes.

## Noncompliant code example

```apex
public class InputProcessor {
    public Boolean hasValue(String input) {
        if (input != null && input != '' && input.trim() != '') {  // Noncompliant
            return true;
        }
        return false;
    }

    public void validate(String name) {
        if (name == null || name == '') {              // Noncompliant
            throw new IllegalArgumentException('Name required');
        }
    }
}
```

## Compliant solution

```apex
public class InputProcessor {
    public Boolean hasValue(String input) {
        return String.isNotBlank(input);
    }

    public void validate(String name) {
        if (String.isBlank(name)) {
            throw new IllegalArgumentException('Name required');
        }
    }
}
```
