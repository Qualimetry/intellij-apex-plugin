# Static initializer blocks must not be empty

`qa-error-handling-empty-static-initializer` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty static { } initializer block serves no purpose. It runs once when the class is loaded but performs no initialization. Remove it to reduce confusion about the class lifecycle.

## Noncompliant code example

```apex
public class CacheService {
    private static Map<String, String> cache;

    static { // Noncompliant - empty static initializer
    }

    public static String get(String key) {
        return cache.get(key);
    }
}
```

## Compliant solution

```apex
public class CacheService {
    private static Map<String, String> cache = new Map<String, String>();

    public static String get(String key) {
        return cache.get(key);
    }
}
```
