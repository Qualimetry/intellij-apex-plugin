# Switch must not have too many case clauses

`qa-complexity-switch-case-limit` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags switch statements with too many when clauses. An excessive number of cases suggests the logic should be refactored using a map, strategy pattern, or polymorphism. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class RegionMapper {
    public String getRegionName(String code) {
        switch on code { // Noncompliant — too many cases
            when 'US' { return 'United States'; }
            when 'UK' { return 'United Kingdom'; }
            when 'DE' { return 'Germany'; }
            when 'FR' { return 'France'; }
            // ... 20+ more cases
            when else { return 'Other'; }
        }
    }
}
```

## Compliant solution

```apex
public class RegionMapper {
    private static final Map<String, String> REGION_MAP = new Map<String, String>{
        'US' => 'United States', 'UK' => 'United Kingdom',
        'DE' => 'Germany', 'FR' => 'France'
    };
    public String getRegionName(String code) {
        return REGION_MAP.containsKey(code) ? REGION_MAP.get(code) : 'Other';
    }
}
```
