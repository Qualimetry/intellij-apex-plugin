# Conditional branches must not repeat the same condition

`qa-error-handling-no-duplicate-condition` &middot; Error Handling &middot; Bug &middot; severity MAJOR &middot; optional

This rule flags if/else if chains and switch statements that contain duplicate conditions. A duplicate condition means the second branch is unreachable or produces confusing behavior. Each branch should test a distinct condition. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class StatusHandler {
    public String getLabel(String status) {
        if (status == 'Active') {
            return 'Active Account';
        } else if (status == 'Inactive') {
            return 'Inactive Account';
        } else if (status == 'Active') { // Noncompliant — duplicate
            return 'Duplicate Active';
        }
        return 'Unknown';
    }
}
```

## Compliant solution

```apex
public class StatusHandler {
    public String getLabel(String status) {
        if (status == 'Active') {
            return 'Active Account';
        } else if (status == 'Inactive') {
            return 'Inactive Account';
        } else if (status == 'Pending') {
            return 'Pending Account';
        }
        return 'Unknown';
    }
}
```
