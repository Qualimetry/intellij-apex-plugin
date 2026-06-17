# Track and report Apex parsing failures

`qa-error-handling-parser-failure` &middot; Error Handling &middot; Code Smell &middot; severity INFO &middot; optional

This rule tracks and reports Apex parsing failures encountered during analysis. Parsing failures indicate files that could not be fully analyzed, which may hide other issues. Investigate and fix syntax errors in flagged files. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class BrokenSyntax {
    public void doWork() {
        // file with syntax errors that cannot be parsed
        String x = ;
    }
}
```

## Compliant solution

```apex
public class CorrectSyntax {
    public void doWork() {
        String x = 'valid';
    }
}
```
