# Switch statements must not be nested

`qa-design-no-nested-switch` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags switch statements that are nested inside other switch statements. Nested switches are difficult to read and indicate that the logic should be extracted into separate methods or simplified with a different approach. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class RouteHandler {
    public void route(String region, String type) {
        switch on region {
            when 'US' {
                switch on type { // Noncompliant — nested switch
                    when 'Premium' { handleUSPremium(); }
                    when else { handleUSStandard(); }
                }
            }
            when else { handleDefault(); }
        }
    }
}
```

## Compliant solution

```apex
public class RouteHandler {
    public void route(String region, String type) {
        switch on region {
            when 'US' { routeUS(type); }
            when else { handleDefault(); }
        }
    }

    private void routeUS(String type) {
        switch on type {
            when 'Premium' { handleUSPremium(); }
            when else { handleUSStandard(); }
        }
    }
}
```
