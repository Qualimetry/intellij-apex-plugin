# Move object instantiation outside of loop bodies

`qa-performance-no-instantiation-in-loop` &middot; Performance &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Creating new object instances inside loop bodies generates unnecessary heap allocation on every iteration, increasing memory consumption and risking the Apex heap size governor limit. Move object instantiation outside the loop and reuse instances where possible, or use collection-based patterns.

## Noncompliant code example

```apex
public class ReportBuilder {
    public void buildReport(List<Account> accounts) {
        for (Account acc : accounts) {
            ReportFormatter formatter = new ReportFormatter();  // Noncompliant
            formatter.format(acc);
        }
    }
}
```

## Compliant solution

```apex
public class ReportBuilder {
    public void buildReport(List<Account> accounts) {
        ReportFormatter formatter = new ReportFormatter();
        for (Account acc : accounts) {
            formatter.format(acc);
        }
    }
}
```
