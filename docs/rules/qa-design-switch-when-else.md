# Switch statements must include when-else clause

`qa-design-switch-when-else` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule requires switch statements to include a when else clause. A missing when else means unexpected values silently fall through without handling. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class StatusMapper {
    public String mapStatus(String code) {
        switch on code { // Noncompliant — missing when else
            when 'A' { return 'Active'; }
            when 'I' { return 'Inactive'; }
        }
        return null;
    }
}
```

## Compliant solution

```apex
public class StatusMapper {
    public String mapStatus(String code) {
        switch on code {
            when 'A' { return 'Active'; }
            when 'I' { return 'Inactive'; }
            when else { return 'Unknown'; }
        }
    }
}
```
