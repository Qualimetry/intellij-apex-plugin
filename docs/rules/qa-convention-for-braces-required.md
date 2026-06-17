# For loop bodies must be enclosed in curly braces

`qa-convention-for-braces-required` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule requires for loop bodies to be enclosed in curly braces, even for single-statement bodies. Omitting braces makes it easy to accidentally add a line that appears to be inside the loop but executes only once. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class BatchUpdater {
    public void markProcessed(List<Account> accounts) {
        for (Account a : accounts)
            a.Description = 'Processed'; // Noncompliant — no braces
        update accounts;
    }
}
```

## Compliant solution

```apex
public class BatchUpdater {
    public void markProcessed(List<Account> accounts) {
        for (Account a : accounts) {
            a.Description = 'Processed';
        }
        update accounts;
    }
}
```
