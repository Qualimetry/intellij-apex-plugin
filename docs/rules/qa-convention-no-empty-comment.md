# Multi-line comment blocks must not be empty

`qa-convention-no-empty-comment` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags multi-line comment blocks (/* */) that contain no text. Empty comments add visual noise without providing value and should be removed. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class EmptyCommentExample {
    /*   */ // Noncompliant — empty comment
    public void doWork() {
        System.debug(LoggingLevel.INFO, 'working');
    }
}
```

## Compliant solution

```apex
public class EmptyCommentExample {
    public void doWork() {
        System.debug(LoggingLevel.INFO, 'working');
    }
}
```
