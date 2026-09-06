# Redundant case conversions should be removed

`qa-design-redundant-case-change` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Applying the same case conversion multiple times in sequence (e.g., calling toLowerCase() on a string that was already lowercased) is redundant. Each additional call wastes CPU cycles and obscures the code without changing the result.

## Noncompliant code example

```apex
public class SearchService {
    public String normalize(String input) {
        String lower = input.toLowerCase();
        return lower.toLowerCase(); // Noncompliant - already lowercased
    }

    public String formatName(String name) {
        String upper = name.toUpperCase();
        return upper.toUpperCase(); // Noncompliant - already uppercased
    }
}
```

## Compliant solution

```apex
public class SearchService {
    public String normalize(String input) {
        return input.toLowerCase();
    }

    public String formatName(String name) {
        return name.toUpperCase();
    }
}
```
