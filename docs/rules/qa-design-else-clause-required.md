# if/else-if chains must end with an else clause

`qa-design-else-clause-required` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule requires if/else if chains to end with an else clause. A missing else may indicate an unhandled case. Adding a final else ensures all branches are explicitly addressed, even if only to throw an exception. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class PriorityMapper {
    public String getPriority(Integer score) {
        if (score > 90) {
            return 'Critical';
        } else if (score > 50) { // Noncompliant — no else
            return 'High';
        }
        return null;
    }
}
```

## Compliant solution

```apex
public class PriorityMapper {
    public String getPriority(Integer score) {
        if (score > 90) {
            return 'Critical';
        } else if (score > 50) {
            return 'High';
        } else {
            return 'Normal';
        }
    }
}
```
