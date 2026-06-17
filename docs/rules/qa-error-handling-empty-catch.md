# Catch blocks must not be empty

`qa-error-handling-empty-catch` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags catch blocks that are empty — containing no statements at all. Empty catch blocks silently swallow exceptions, hiding errors and making debugging extremely difficult. At minimum, log the exception or add a comment explaining why it is intentionally ignored. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class DataLoader {
    public void loadData(String payload) {
        try {
            List<Account> accounts = (List<Account>) JSON.deserialize(payload, List<Account>.class);
            insert accounts;
        } catch (Exception e) {
            // Noncompliant — empty catch
        }
    }
}
```

## Compliant solution

```apex
public class DataLoader {
    public void loadData(String payload) {
        try {
            List<Account> accounts = (List<Account>) JSON.deserialize(payload, List<Account>.class);
            insert accounts;
        } catch (Exception e) {
            System.debug(LoggingLevel.ERROR, 'Data load failed: ' + e.getMessage());
        }
    }
}
```
