# String case conversions must specify a locale

`qa-design-locale-in-case-conversion` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

String case conversions like toLowerCase() and toUpperCase() produce different results depending on the locale. In some locales, characters like the Turkish dotted and dotless 'i' convert differently. Specifying the locale explicitly ensures consistent behavior across all user environments.

## Noncompliant code example

```apex
public class SearchNormalizer {
    public String normalize(String input) {
        return input.toLowerCase(); // Noncompliant - no locale specified
    }

    public String formatCode(String code) {
        return code.toUpperCase(); // Noncompliant - no locale specified
    }
}
```

## Compliant solution

```apex
public class SearchNormalizer {
    public String normalize(String input) {
        return input.toLowerCase('en_US');
    }

    public String formatCode(String code) {
        return code.toUpperCase('en_US');
    }
}
```
