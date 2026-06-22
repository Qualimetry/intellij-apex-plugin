# Throwable should not be caught directly

`qa-error-handling-no-catch-throwable` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Catching Throwable or Error captures system-level problems like OutOfMemoryError that the application cannot meaningfully handle. Catching too broadly masks real issues and may prevent the runtime from performing necessary cleanup. Catch specific exception types instead.

## Noncompliant code example

```apex
public class DataImporter {
    public void importRecords(List<Account> records) {
        try {
            insert records;
        } catch (Throwable t) { // Noncompliant - catches too broadly
            System.debug('Error: ' + t.getMessage());
        }
    }

    public void processData(String data) {
        try {
            Integer val = Integer.valueOf(data);
        } catch (Throwable t) { // Noncompliant
            System.debug(t);
        }
    }
}
```

## Compliant solution

```apex
public class DataImporter {
    public void importRecords(List<Account> records) {
        try {
            insert records;
        } catch (DmlException e) {
            System.debug('DML Error: ' + e.getMessage());
        }
    }

    public void processData(String data) {
        try {
            Integer val = Integer.valueOf(data);
        } catch (TypeException e) {
            System.debug('Invalid format: ' + e.getMessage());
        }
    }
}
```
