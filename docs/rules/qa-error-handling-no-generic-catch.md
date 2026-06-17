# Catch specific exception types rather than generic Exception

`qa-error-handling-no-generic-catch` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags catch blocks that catch the generic Exception type instead of specific exception classes. Catching Exception masks unexpected errors and prevents appropriate handling of different failure modes. Catch the most specific exception types relevant to the operation. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class DataImporter {
    public void importData(String payload) {
        try {
            List<Account> accounts = (List<Account>) JSON.deserialize(payload, List<Account>.class);
            insert accounts;
        } catch (Exception e) { // Noncompliant — too broad
            System.debug(LoggingLevel.ERROR, e.getMessage());
        }
    }
}
```

## Compliant solution

```apex
public class DataImporter {
    public void importData(String payload) {
        try {
            List<Account> accounts = (List<Account>) JSON.deserialize(payload, List<Account>.class);
            insert accounts;
        } catch (JSONException je) {
            System.debug(LoggingLevel.ERROR, 'Invalid JSON: ' + je.getMessage());
        } catch (DmlException de) {
            System.debug(LoggingLevel.ERROR, 'DML failed: ' + de.getMessage());
        }
    }
}
```
