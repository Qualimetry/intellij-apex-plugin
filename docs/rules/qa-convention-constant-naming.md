# Constant field names must use UPPER_SNAKE_CASE

`qa-convention-constant-naming` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule requires constant field names to use UPPER_SNAKE_CASE. Constants in camelCase or other patterns are harder to distinguish from regular variables. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AppConstants {
    public static final String defaultStatus = 'Active'; // Noncompliant
    public static final Integer maxRetries = 3; // Noncompliant
}
```

## Compliant solution

```apex
public class AppConstants {
    public static final String DEFAULT_STATUS = 'Active';
    public static final Integer MAX_RETRIES = 3;
}
```
