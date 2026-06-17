# Conditions that always true/false must be removed

`qa-error-handling-unconditional-if` &middot; Error Handling &middot; Bug &middot; severity MAJOR &middot; optional

This rule flags if statements with conditions that always evaluate to true or false, such as if (true) or if (false). These conditions make code unreachable or always executed, suggesting a logic error or leftover debugging code. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class FeatureService {
    public void doWork() {
        if (true) { // Noncompliant — always true
            System.debug(LoggingLevel.INFO, 'always runs');
        }
        if (false) { // Noncompliant — dead code
            System.debug(LoggingLevel.INFO, 'never runs');
        }
    }
}
```

## Compliant solution

```apex
public class FeatureService {
    public void doWork() {
        System.debug(LoggingLevel.INFO, 'always runs');
    }
}
```
