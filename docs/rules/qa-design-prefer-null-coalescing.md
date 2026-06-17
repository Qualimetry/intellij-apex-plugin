# Prefer the null coalescing operator over ternary null checks

`qa-design-prefer-null-coalescing` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Apex supports the null coalescing operator ??, which provides a cleaner alternative to ternary null checks like x != null ? x : defaultValue. Using x ?? defaultValue is shorter, more expressive, and reduces the chance of accidentally reversing the condition branches.

## Noncompliant code example

```apex
public class ConfigReader {
    public String getEndpoint(String override) {
        return override != null ? override : 'https://default.api.com'; // Noncompliant
    }

    public Integer getTimeout(Integer custom) {
        return custom != null ? custom : 30000; // Noncompliant
    }
}
```

## Compliant solution

```apex
public class ConfigReader {
    public String getEndpoint(String override) {
        return override ?? 'https://default.api.com';
    }

    public Integer getTimeout(Integer custom) {
        return custom ?? 30000;
    }
}
```
