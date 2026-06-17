# Methods must not exceed the maximum line count

`qa-complexity-method-line-limit` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags methods that exceed the configured maximum line count. Long methods are hard to understand, test, and maintain. Extract logical sections into well-named helper methods. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class ReportBuilder {
    public String buildReport() { // Noncompliant — too many lines
        // 100+ lines of mixed logic
        String header = 'Report';
        // ... many more lines
        return header;
    }
}
```

## Compliant solution

```apex
public class ReportBuilder {
    public String buildReport() {
        String header = buildHeader();
        String body = buildBody();
        return header + body;
    }
    private String buildHeader() { return 'Report Header'; }
    private String buildBody() { return 'Report Body'; }
}
```
