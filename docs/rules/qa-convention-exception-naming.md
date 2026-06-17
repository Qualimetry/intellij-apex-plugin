# Custom exception names must end with Exception

`qa-convention-exception-naming` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule requires custom exception class names to end with Exception. Following the naming convention makes exception types immediately recognizable in catch blocks and improves code readability. Classes that extend Exception but do not follow this convention create confusion. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class InvalidDataError extends Exception { // Noncompliant — should end with Exception
    public String fieldName;
}
```

## Compliant solution

```apex
public class InvalidDataException extends Exception {
    public String fieldName;
}
```
