# Catch blocks must add value beyond rethrowing

`qa-error-handling-catch-must-add-value` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A catch block that only rethrows the exception or performs trivial logging without any recovery logic adds no value to the error handling chain. Each catch block should either transform the exception with added context, perform compensating actions, or handle the error meaningfully.

## Noncompliant code example

```apex
public class RecordService {
    public void save(Account acc) {
        try {
            upsert acc;
        } catch (DmlException e) { // Noncompliant - only rethrows
            throw e;
        }
    }

    public void cleanup(Id recordId) {
        try {
            delete [SELECT Id FROM Account WHERE Id = :recordId];
        } catch (Exception e) { // Noncompliant - only rethrows
            throw e;
        }
    }
}
```

## Compliant solution

```apex
public class RecordService {
    public void save(Account acc) {
        try {
            upsert acc;
        } catch (DmlException e) {
            Logger.error('Failed to save account: ' + acc.Name, e);
            throw new ApplicationException('Save failed', e);
        }
    }

    public void cleanup(Id recordId) {
        delete [SELECT Id FROM Account WHERE Id = :recordId];
    }
}
```
